package io.github.anugrahrochmat.moviecatalogue.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import io.github.anugrahrochmat.moviecatalogue.database.MovieContract;

public class FavouritesMoviesInteractor extends AsyncTask<Void, Void, Cursor> {

    private Context context;
    private FavouritesMoviesPresenter favouritesMoviesPresenter;

    public FavouritesMoviesInteractor(FavouritesMoviesPresenter favouritesMoviesPresenter, Context context) {
        this.favouritesMoviesPresenter = favouritesMoviesPresenter;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        favouritesMoviesPresenter.onProcessStart();
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        return context.getContentResolver()
                .query(MovieContract.MovieEntry.CONTENT_URI,
                        MovieContract.MovieEntry.MOVIE_COLUMNS,
                        null,
                        null,
                        null);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        favouritesMoviesPresenter.loadMovies(cursor);
    }
}
