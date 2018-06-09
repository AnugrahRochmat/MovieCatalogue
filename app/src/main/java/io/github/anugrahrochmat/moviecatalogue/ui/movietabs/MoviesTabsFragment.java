package io.github.anugrahrochmat.moviecatalogue.ui.movietabs;


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
import io.github.anugrahrochmat.moviecatalogue.data.models.movie.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesTabsFragment extends Fragment implements MoviesTabsView {

    private final String SAVED_MOVIES_LIST = "SAVED_MOVIES_LIST";

    @BindView(R.id.rv_movies_list)      RecyclerView rvMoviesList;
    @BindView(R.id.progress_bar)        ProgressBar progressBar;
    @BindView(R.id.tv_error_message)    TextView tvErrorMessage;

    private MoviesListPresenter presenter;
    private MoviesListAdapter adapter;
    private String sortBy;

    public MoviesTabsFragment() {
        // Required empty public constructor
    }

    public static MoviesTabsFragment newInstance(String sortBy){
        MoviesTabsFragment moviesTabsFragment = new MoviesTabsFragment();

        Bundle args = new Bundle();
        args.putString("sort_by", sortBy);
        moviesTabsFragment.setArguments(args);

        return moviesTabsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_tabs, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

//        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
//        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        sortBy = getArguments().getString("sort_by");
        presenter = new MoviesListPresenter(this);
        presenter.onStartView(sortBy);

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(SAVED_MOVIES_LIST)) {
                List<Movie> movieList = savedInstanceState.getParcelableArrayList(SAVED_MOVIES_LIST);
                presenter.loadMovies(movieList);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Movie> moviesSaved = new ArrayList<>(adapter.getMovieList());
        if (moviesSaved != null) {
            outState.putParcelableArrayList(SAVED_MOVIES_LIST, moviesSaved);
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
