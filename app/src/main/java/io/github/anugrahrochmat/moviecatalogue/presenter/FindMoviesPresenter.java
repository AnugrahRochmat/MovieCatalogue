package io.github.anugrahrochmat.moviecatalogue.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;

public class FindMoviesPresenter {

    private FindMoviesView findMoviesView;

    public FindMoviesPresenter(FindMoviesView findMoviesView) {
        this.findMoviesView = findMoviesView;
    }

    public void onStartView(){
        if (isNetworkAvailable()) {
            findMoviesView.initializeRV();
        } else {
            findMoviesView.displayNoMovies();
            findMoviesView.onShowError(findMoviesView.getContext().getResources().getString(R.string.error_no_connection));
        }
    }

    public void initializeFindButton(){
        findMoviesView.onFindBtnClicked();
        findMoviesView.onHandleEditText();
    }

    public void loadMovies(List<Movie> movieList) {
        if (movieList.isEmpty()){
            findMoviesView.onHideProgress();
            findMoviesView.displayNoMovies();
            findMoviesView.onShowError(findMoviesView.getContext().getResources().getString(R.string.can_not_find_movie));
        } else {
            findMoviesView.onHideProgress();
            findMoviesView.onHideError();
            findMoviesView.displayMovies(movieList);
        }
    }

    public void onSearchBtnClick(String query){
        if (TextUtils.isEmpty(query)){
            findMoviesView.onSearchQueryError(findMoviesView.getContext().getString(R.string.find_movie_empty));
            return;
        } else {
            findMoviesView.onSearchQueryError(null);
            if (isNetworkAvailable()) {
                new FindMoviesInteractor(query, this).execute();
            } else {
                findMoviesView.displayNoMovies();
                findMoviesView.onShowError(findMoviesView.getContext().getResources().getString(R.string.error_no_connection));
            }
        }
    }

    public void onProcessStart(){
        findMoviesView.onHideError();
        findMoviesView.onShowProgress();
    }

    public void onErrorFetchMovie(){
        findMoviesView.onHideProgress();
        findMoviesView.displayNoMovies();
        findMoviesView.onShowError(findMoviesView.getContext().getResources().getString(R.string.error_no_connection));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) findMoviesView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
