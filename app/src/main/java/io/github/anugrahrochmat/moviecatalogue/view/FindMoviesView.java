package io.github.anugrahrochmat.moviecatalogue.view;

import android.content.Context;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.model.Movie;

public interface FindMoviesView {

    Context getContext();

    void initializeRV();

    void displayMovies(List<Movie> movieList);

    void displayNoMovies();

    void onFindBtnClicked();

    void onHandleEditText();

    void onSearchQueryError(String msg);

    void onShowProgress();

    void onHideProgress();

    void onShowError(String msg);

    void onHideError();

}
