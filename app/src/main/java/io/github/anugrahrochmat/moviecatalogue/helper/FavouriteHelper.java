package io.github.anugrahrochmat.moviecatalogue.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import io.github.anugrahrochmat.moviecatalogue.database.MovieContract;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.presenter.DetailMoviePresenter;

public class FavouriteHelper {

    private Movie movie;
    private DetailMoviePresenter detailMoviePresenter;

    public FavouriteHelper(DetailMoviePresenter detailMoviePresenter) {
        this.detailMoviePresenter = detailMoviePresenter;
    }

    /**
     * isFavourite method
     */
    public boolean isFavourite(String movieId, Context context){
        Uri uri = MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendEncodedPath(movieId).build();
        Cursor cursor = context.getContentResolver().query(uri,
                null,
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ? ",
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    /**
     * addFavourite Method
     */
    @SuppressLint("StaticFieldLeak")
    public void addFavourite(final Movie movie, final Context context){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                if (!isFavourite(movie.getId().toString(), context)) {
                    ContentValues contentValues = new ContentValues();

                    contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, movie.getId());
                    contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE, movie.getOriginalTitle());
                    contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW, movie.getOverview());
                    contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_BACKDROP, movie.getBackdropPath());
                    contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER, movie.getPosterPath());
                    contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE, movie.getReleaseDate());
                    contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_VOTE_AVERAGE, movie.getVoteAverage());

                    context.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                detailMoviePresenter.onShowFavourite();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * removeFavourites Method
     */
    @SuppressLint("StaticFieldLeak")
    public void removeFavourites(final String movieId, final Context context){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                if (isFavourite(movieId, context)) {
                    Uri uri = MovieContract.MovieEntry.CONTENT_URI.buildUpon().appendEncodedPath(movieId).build();
                    context.getContentResolver().delete(uri, null, null);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                detailMoviePresenter.onHideFavourite();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
