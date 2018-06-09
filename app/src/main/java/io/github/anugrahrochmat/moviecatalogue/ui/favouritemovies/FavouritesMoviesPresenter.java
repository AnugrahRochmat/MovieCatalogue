package io.github.anugrahrochmat.moviecatalogue.ui.favouritemovies;

import android.database.Cursor;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.data.models.movie.Movie;

public class FavouritesMoviesPresenter {

    private FavouritesMoviesView favouritesMoviesView;

    public FavouritesMoviesPresenter(FavouritesMoviesView favouritesMoviesView) {
        this.favouritesMoviesView = favouritesMoviesView;
    }

    public void onStartView(){
        favouritesMoviesView.initializeRV();
        new FavouritesMoviesInteractor(this, favouritesMoviesView.getContext()).execute();
    }

    public void loadMovies(Cursor movieCursor) {
        if (movieCursor.getCount() == 0){
            favouritesMoviesView.onHideProgress();
            favouritesMoviesView.displayNoMovies();
            favouritesMoviesView.onShowError(favouritesMoviesView.getContext().getResources().getString(R.string.empty_favourites));
        } else {
            favouritesMoviesView.onHideProgress();
            favouritesMoviesView.onHideError();
            favouritesMoviesView.displayMovies(movieCursor);
        }
    }

    public void loadMovies(List<Movie> movieList) {
        if (movieList.isEmpty()){
            favouritesMoviesView.onHideProgress();
            favouritesMoviesView.displayNoMovies();
            favouritesMoviesView.onShowError(favouritesMoviesView.getContext().getResources().getString(R.string.empty_favourites));
        } else {
            favouritesMoviesView.onHideProgress();
            favouritesMoviesView.onHideError();
            favouritesMoviesView.displayMovies(movieList);
        }
    }

    public void onProcessStart(){
        favouritesMoviesView.onHideError();
        favouritesMoviesView.onShowProgress();
    }

}
