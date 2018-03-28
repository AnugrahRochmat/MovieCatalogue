package io.github.anugrahrochmat.moviecatalogue.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.adapter.FindMoviesAdapter;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.presenter.FindMoviesPresenter;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;

public class FindMoviesActivity extends AppCompatActivity implements FindMoviesView{

    @BindView(R.id.edt_find_movie)          EditText edtFindMovie;
    @BindView(R.id.btn_find_movie)          ImageButton btnFindMovie;
    @BindView(R.id.rv_find_movies_result)   RecyclerView rvFindMoviesResult;
    @BindView(R.id.progress_bar)            ProgressBar progressBar;
    @BindView(R.id.tv_error_message)        TextView tvErrorMessage;

    private FindMoviesPresenter presenter;
    private FindMoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_movies);
        ButterKnife.bind(this);

        presenter = new FindMoviesPresenter(this);
        //onFindBtnClicked();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initializeRV() {

    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvFindMoviesResult.setLayoutManager(layoutManager);
        rvFindMoviesResult.setHasFixedSize(true);

        adapter = new FindMoviesAdapter(new ArrayList<Movie>(), getContext());
        rvFindMoviesResult.setAdapter(adapter);
        adapter.setMovieList(movieList);

        rvFindMoviesResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayNoMovies() {
        rvFindMoviesResult.setVisibility(View.GONE);
    }

    @Override
    public void onFindBtnClicked() {
        // Find Button CLicked
        btnFindMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtFindMovie.getWindowToken(), 0);
                presenter.onSearchBtnClick(edtFindMovie.getText().toString().trim());
            }
        });
        onHandleEditText();
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
