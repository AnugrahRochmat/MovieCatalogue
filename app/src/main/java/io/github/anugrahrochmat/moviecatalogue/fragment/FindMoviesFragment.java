package io.github.anugrahrochmat.moviecatalogue.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.adapter.FindMoviesAdapter;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.presenter.FindMoviesPresenter;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;

public class FindMoviesFragment extends Fragment implements FindMoviesView {

    private final String SAVED_FIND_MOVIES_RESULT = "SAVED_FIND_MOVIES_RESULT";
    private final String SAVED_EDIT_TEXT = "SAVED_EDIT_TEXT";

    @BindView(R.id.edt_find_movie)          EditText edtFindMovie;
    @BindView(R.id.btn_find_movie)          ImageButton btnFindMovie;
    @BindView(R.id.rv_find_movies_result)   RecyclerView rvFindMoviesResult;
    @BindView(R.id.progress_bar)            ProgressBar progressBar;
    @BindView(R.id.tv_error_message)        TextView tvErrorMessage;

    private FindMoviesPresenter presenter;
    private FindMoviesAdapter adapter;

    public FindMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_movies, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        presenter = new FindMoviesPresenter(this);

        presenter.onStartView();
        presenter.initializeFindButton();

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(SAVED_EDIT_TEXT)) {
                edtFindMovie.setText(savedInstanceState.getString(SAVED_EDIT_TEXT));
            }
            if (savedInstanceState.containsKey(SAVED_FIND_MOVIES_RESULT)) {
                List<Movie> movieList = savedInstanceState.getParcelableArrayList(SAVED_FIND_MOVIES_RESULT);
                presenter.loadMovies(movieList);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Movie> moviesSaved = new ArrayList<>(adapter.getMovieList());
        String edtTextSaved = edtFindMovie.getText().toString().trim();
        if (moviesSaved != null) {
            outState.putParcelableArrayList(SAVED_FIND_MOVIES_RESULT, moviesSaved);
        }
        if (edtTextSaved != null && !edtTextSaved.isEmpty()) {
            outState.putString(SAVED_EDIT_TEXT, edtTextSaved);
        }
    }

    // Implement Methods from FindMoviesView
    @Override
    public void initializeRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvFindMoviesResult.setLayoutManager(layoutManager);
        rvFindMoviesResult.setHasFixedSize(true);

        adapter = new FindMoviesAdapter(new ArrayList<Movie>(), getContext());
        rvFindMoviesResult.setAdapter(adapter);
    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        adapter.setMovieList(movieList);
        rvFindMoviesResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayNoMovies() {
        adapter.setMovieList(Collections.<Movie>emptyList());
        rvFindMoviesResult.setVisibility(View.GONE);
    }

    @Override
    public void onFindBtnClicked() {
        // Find Button CLicked
        btnFindMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtFindMovie.getWindowToken(), 0);
                presenter.onSearchBtnClick(edtFindMovie.getText().toString().trim());
            }
        });
    }

    @Override
    public void onHandleEditText() {
        // Edit Text View enter pressed equal to button being clicked
        edtFindMovie.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager in = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(edtFindMovie.getWindowToken(), 0);

                    btnFindMovie.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onSearchQueryError(String msg) {
        edtFindMovie.setError(msg);
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
