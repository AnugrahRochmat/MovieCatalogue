package io.github.anugrahrochmat.moviecatalogue.view;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.model.Movie;

public interface FindMoviesView {

    void displayMovies(List<Movie> movieList);

    void displayNoMovies();
}
