package io.github.anugrahrochmat.moviecatalogue.ui.movietabs;

import android.content.Context;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.data.models.movie.Movie;

public interface MoviesTabsView {

    Context getContext();

    void initializeRV();

    void displayMovies(List<Movie> movieList);

    void displayNoMovies();

    void onShowProgress();

    void onHideProgress();

    void onShowError(String msg);

    void onHideError();
}
