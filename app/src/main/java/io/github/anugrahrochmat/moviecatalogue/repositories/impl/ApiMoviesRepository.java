package io.github.anugrahrochmat.moviecatalogue.repositories.impl;

import android.content.Context;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.repositories.MoviesRepository;

public class ApiMoviesRepository implements MoviesRepository{

    public ApiMoviesRepository(Context context) {

    }

    @Override
    public List<Movie> getMovies() {
        return null;
    }



}
