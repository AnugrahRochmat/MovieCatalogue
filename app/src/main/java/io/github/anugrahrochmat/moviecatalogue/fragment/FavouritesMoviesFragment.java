package io.github.anugrahrochmat.moviecatalogue.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.adapter.MoviesListAdapter;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.presenter.FavouritesMoviesPresenter;
import io.github.anugrahrochmat.moviecatalogue.view.FavouritesMoviesView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesMoviesFragment extends Fragment implements FavouritesMoviesView {

    private final String SAVED_FAVOURITES_MOVIES_LIST = "SAVED_FAVOURITES_MOVIES_LIST";

    @BindView(R.id.rv_movies_list)      RecyclerView rvMoviesList;
    @BindView(R.id.progress_bar)        ProgressBar progressBar;
    @BindView(R.id.tv_error_message)    TextView tvErrorMessage;

    private FavouritesMoviesPresenter presenter;
    private MoviesListAdapter adapter;

    public FavouritesMoviesFragment() {
        // Required empty public constructor
    }

    public static FavouritesMoviesFragment newInstance(){
        FavouritesMoviesFragment favouritesMoviesFragment = new FavouritesMoviesFragment();
        return favouritesMoviesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites_movies, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        presenter = new FavouritesMoviesPresenter(this);
        presenter.onStartView();

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(SAVED_FAVOURITES_MOVIES_LIST)) {
                List<Movie> movieList = savedInstanceState.getParcelableArrayList(SAVED_FAVOURITES_MOVIES_LIST);
                presenter.loadMovies(movieList);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Movie> moviesSaved = new ArrayList<>(adapter.getMovieList());
        if (moviesSaved != null) {
            outState.putParcelableArrayList(SAVED_FAVOURITES_MOVIES_LIST, moviesSaved);
        }
    }

    @Override
    public void initializeRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMoviesList.setLayoutManager(layoutManager);
        rvMoviesList.setHasFixedSize(true);

        adapter = new MoviesListAdapter(new ArrayList<Movie>(), getContext());
        rvMoviesList.setAdapter(adapter);
    }

    @Override
    public void displayMovies(Cursor movieCursor) {
        adapter.add(movieCursor);
        rvMoviesList.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        adapter.setMovieList(movieList);
        rvMoviesList.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayNoMovies() {
        adapter.setMovieList(Collections.<Movie>emptyList());
        rvMoviesList.setVisibility(View.GONE);
    }

    @Override
    public void onShowProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onShowError(String msg) {
        tvErrorMessage.setText(msg);
        tvErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideError() {
        tvErrorMessage.setVisibility(View.GONE);
    }
}

