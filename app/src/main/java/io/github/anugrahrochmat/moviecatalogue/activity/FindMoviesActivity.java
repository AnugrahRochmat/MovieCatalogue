package io.github.anugrahrochmat.moviecatalogue.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.presenter.FindMoviesPresenter;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;

public class FindMoviesActivity extends AppCompatActivity implements FindMoviesView{

    @BindView(R.id.edt_find_movie)          EditText edtFindMovie;
    @BindView(R.id.btn_find_movie)          Button  btnFindMovie;
    @BindView(R.id.rv_find_movies_result)   RecyclerView rvFindMoviesResult;
    @BindView(R.id.progress_bar)            ProgressBar progressBar;
    @BindView(R.id.tv_error_message)        TextView tvErrorMessage;

    private FindMoviesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_movies);
        ButterKnife.bind(this);

        presenter = new FindMoviesPresenter(this, null);
    }

    @Override
    public void displayMovies(List<Movie> movieList) {

    }

    @Override
    public void displayNoMovies() {

    }

}
