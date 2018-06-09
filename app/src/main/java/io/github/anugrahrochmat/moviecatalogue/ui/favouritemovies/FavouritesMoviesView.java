package io.github.anugrahrochmat.moviecatalogue.ui.favouritemovies;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.data.models.movie.Movie;

public interface FavouritesMoviesView {

    Context getContext();

    void initializeRV();

    void displayMovies(Cursor movieCursor);

    void displayMovies(List<Movie> movieList);

    void displayNoMovies();

    void onShowProgress();

    void onHideProgress();

    void onShowError(String msg);

    void onHideError();
}
