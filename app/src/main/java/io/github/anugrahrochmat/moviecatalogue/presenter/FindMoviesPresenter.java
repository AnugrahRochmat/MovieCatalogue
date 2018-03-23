package io.github.anugrahrochmat.moviecatalogue.presenter;

import android.text.TextUtils;

import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.repositories.MoviesRepository;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;

public class FindMoviesPresenter {

    private FindMoviesView findMoviesView;
    private MoviesRepository moviesRepository;

    public FindMoviesPresenter(FindMoviesView findMoviesView, MoviesRepository moviesRepository) {
        this.findMoviesView = findMoviesView;
        this.moviesRepository = moviesRepository;
    }

//    public void loadMovies() {
//        List<Movie> movieList = moviesRepository.getMovies();
//        if (movieList.isEmpty()){
//            findMoviesView.onHideProgress();
//            findMoviesView.displayNoMovies();
//        } else {
//            findMoviesView.onHideProgress();
//            findMoviesView.displayMovies(movieList);
//        }
//    }

    public void onSearchBtnClick(String query){
        if (TextUtils.isEmpty(query)){
            findMoviesView.onSearchQueryError(findMoviesView.getContext().getString(R.string.error_no_connection));
            return;
        } else {
            findMoviesView.onSearchQueryError(null);
        }
        // ASyncTask Execute
    }

    public void onProcessStart(){
        findMoviesView.onShowProgress();
    }

    public void onErrorConnection(){
        findMoviesView.onErrorNoConnection();
    }

}
