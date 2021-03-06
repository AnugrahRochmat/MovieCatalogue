package io.github.anugrahrochmat.moviecatalogue.ui.moviedetail;

import android.os.Bundle;

import io.github.anugrahrochmat.moviecatalogue.util.helper.FavouriteHelper;
import io.github.anugrahrochmat.moviecatalogue.data.models.movie.Movie;

public class DetailMoviePresenter {

    private DetailMovieView detailMovieView;
    private FavouriteHelper favouriteHelper;

    public DetailMoviePresenter(DetailMovieView detailMovieView) {
        this.detailMovieView = detailMovieView;
    }

    public void onStartFragment(Bundle data){
        if (data != null) {
            detailMovieView.displayDetailMovie(data);
            favouriteHelper = new FavouriteHelper(this);
        }
    }

    public boolean onCheckStatusFavourites(Movie movie){
        return favouriteHelper.isFavourite(movie.getId().toString(), detailMovieView.getContext());
    }

    public void onInitFav(Movie movie){
        if (onCheckStatusFavourites(movie)){
            onShowFavourite();
        } else {
            onHideFavourite();
        }
    }

    public void updateFavourites(Movie movie){
        if (!onCheckStatusFavourites(movie)){
            favouriteHelper.addFavourite(movie, detailMovieView.getContext());
        } else {
            favouriteHelper.removeFavourites(movie.getId().toString(), detailMovieView.getContext());
        }
    }

    public void onShowFavourite(){
        detailMovieView.onFavouriteTrue();
    }

    public void onHideFavourite(){
        detailMovieView.onFavouriteFalse();
    }
}
