package io.github.anugrahrochmat.moviecatalogue.repositories;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.model.Movie;

public interface MoviesRepository {

    List<Movie> getMovies();
}
