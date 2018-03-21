package io.github.anugrahrochmat.moviecatalogue.presenter;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.repositories.MoviesRepository;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;

public class FindMoviesPresenter {

    private FindMoviesView view;
    private MoviesRepository moviesRepository;

    public FindMoviesPresenter(FindMoviesView view, MoviesRepository moviesRepository) {
        this.view = view;
        this.moviesRepository = moviesRepository;
    }

    public void loadMovies() {
        List<Movie> movieList = moviesRepository.getMovies();
        if (movieList.isEmpty()){
            view.displayNoMovies();
        } else {
            view.displayMovies(movieList);
        }
    }
}
