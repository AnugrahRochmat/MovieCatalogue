package io.github.anugrahrochmat.moviecatalogue.presenter;

import android.os.Bundle;

import io.github.anugrahrochmat.moviecatalogue.view.DetailMovieView;

public class DetailMoviePresenter {

    private DetailMovieView detailMovieView;

    public DetailMoviePresenter(DetailMovieView detailMovieView) {
        this.detailMovieView = detailMovieView;
    }

    public void onStartFragment(Bundle data){
        if (data != null) {
            detailMovieView.displayDetailMovie(data);
        }
    }

}
