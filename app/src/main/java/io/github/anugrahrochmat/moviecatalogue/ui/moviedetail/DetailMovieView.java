package io.github.anugrahrochmat.moviecatalogue.ui.moviedetail;

import android.content.Context;
import android.os.Bundle;

public interface DetailMovieView {

    Context getContext();

    void displayDetailMovie(Bundle data);

    void onFavouriteTrue();

    void onFavouriteFalse();
}
