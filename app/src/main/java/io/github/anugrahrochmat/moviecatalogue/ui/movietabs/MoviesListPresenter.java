package io.github.anugrahrochmat.moviecatalogue.ui.movietabs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.data.models.movie.Movie;

public class MoviesListPresenter {

    private MoviesTabsView moviesTabsView;

    public MoviesListPresenter(MoviesTabsView moviesTabsView) {
        this.moviesTabsView = moviesTabsView;
    }

    public void onStartView(String sortBy){
        if (isNetworkAvailable()) {
            moviesTabsView.initializeRV();
            new MoviesListInteractor(sortBy, this).execute();
        } else {
            moviesTabsView.displayNoMovies();
            moviesTabsView.onShowError(moviesTabsView.getContext().getResources().getString(R.string.error_no_connection));
        }
    }

    public void loadMovies(List<Movie> movieList) {
        if (movieList.isEmpty()){
            moviesTabsView.onHideProgress();
            moviesTabsView.displayNoMovies();
            moviesTabsView.onShowError(moviesTabsView.getContext().getResources().getString(R.string.can_not_find_movie));
        } else {
            moviesTabsView.onHideProgress();
            moviesTabsView.onHideError();
            moviesTabsView.displayMovies(movieList);
        }
    }

    public void onProcessStart(){
        moviesTabsView.onHideError();
        moviesTabsView.onShowProgress();
    }

    public void onErrorFetchMovie(){
        moviesTabsView.onHideProgress();
        moviesTabsView.displayNoMovies();
        moviesTabsView.onShowError(moviesTabsView.getContext().getResources().getString(R.string.error_no_connection));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) moviesTabsView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
