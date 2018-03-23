package io.github.anugrahrochmat.moviecatalogue.presenter;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.repositories.MoviesRepository;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;

public class FindMoviesPresenterTest {

    @Test
    public void shouldPassMoviesToView(){
        // given (initial conditions)
        FindMoviesView view = new MockView();
        MoviesRepository moviesRepository = new MockMoviesRepository(true);

        // when (action to trigger)
        FindMoviesPresenter presenter = new FindMoviesPresenter(view, moviesRepository);
        presenter.loadMovies();

        // then (did it work or not work)
        Assert.assertEquals(true, ((MockView) view).displayWithMovies);
    }

    @Test
    public void shouldHandleNoMoviesFound(){
        // given (initial conditions)
        FindMoviesView view = new MockView();
        MoviesRepository moviesRepository = new MockMoviesRepository(false);

        // when (action to trigger)
        FindMoviesPresenter presenter = new FindMoviesPresenter(view, moviesRepository);
        presenter.loadMovies();

        // then (did it work or not work)
        Assert.assertEquals(true , ((MockView) view).displayWithNoMovies);
    }

    private class MockView implements FindMoviesView {

        boolean displayWithMovies;
        boolean displayWithNoMovies;

        @Override
        public Context getContext() {
            return null;
        }

        @Override
        public void displayMovies(List<Movie> movieList) {
            if (movieList.size() == 3) displayWithMovies = true;
        }

        @Override
        public void displayNoMovies() {
            displayWithNoMovies = true;
        }

        @Override
        public void onSearchQueryError(String msg) {

        }

        @Override
        public void onShowProgress() {

        }

        @Override
        public void onHideProgress() {

        }

        @Override
        public void onErrorNoConnection() {

        }
    }

    private class MockMoviesRepository implements MoviesRepository {

        private boolean returnSomeMovies;

        public MockMoviesRepository(boolean returnSomeMovies) {
            this.returnSomeMovies = returnSomeMovies;
        }

        @Override
        public List<Movie> getMovies() {
            if (returnSomeMovies){
                return Arrays.asList(new Movie(), new Movie(), new Movie());
            } else {
                return Collections.emptyList();
            }

        }
    }
}