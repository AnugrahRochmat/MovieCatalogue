package io.github.anugrahrochmat.moviecatalogue.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.BuildConfig;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.adapter.FindMoviesAdapter;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.model.MoviesResponse;
import io.github.anugrahrochmat.moviecatalogue.presenter.FindMoviesPresenter;
import io.github.anugrahrochmat.moviecatalogue.rest.ApiClient;
import io.github.anugrahrochmat.moviecatalogue.rest.ApiInterface;
import io.github.anugrahrochmat.moviecatalogue.view.FindMoviesView;
import retrofit2.Call;
import retrofit2.Response;

public class FindMoviesActivity extends AppCompatActivity implements FindMoviesView{

    @BindView(R.id.edt_find_movie)          EditText edtFindMovie;
    @BindView(R.id.btn_find_movie)          ImageButton btnFindMovie;
    @BindView(R.id.rv_find_movies_result)   RecyclerView rvFindMoviesResult;
    @BindView(R.id.progress_bar)            ProgressBar progressBar;
    @BindView(R.id.tv_error_message)        TextView tvErrorMessage;

    private FindMoviesPresenter presenter;

    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String TAG = MainActivity.class.getSimpleName();
    private FindMoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_movies);
        ButterKnife.bind(this);

        presenter = new FindMoviesPresenter(this, null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvFindMoviesResult.setLayoutManager(layoutManager);
        rvFindMoviesResult.setHasFixedSize(true);

        adapter = new FindMoviesAdapter(new ArrayList<Movie>(), getContext());
        rvFindMoviesResult.setAdapter(adapter);

        new FetchMoviesTask().execute();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void displayMovies(List<Movie> movieList) {

    }

    @Override
    public void displayNoMovies() {

    }

    @Override
    public void onSearchQueryError(String msg) {

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
    public void onErrorNoConnection() {

    }


    /**
     * Fetch Movies from API
     */
    public class FetchMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onShowProgress();
        }

        @Override
        protected List<Movie> doInBackground(Void... params) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<MoviesResponse> call = apiService.getFindMovies(API_KEY, "Captain America");

            try {
                Response<MoviesResponse> response = call.execute();
                List<Movie> movies = response.body().getResults();
                return movies;
            } catch (IOException e){
                Log.e(TAG, "A problem occured ", e);
            }
            return null;
        }

//        @Override
//        protected void onPostExecute(List<Movie> movies) {
//            onHideProgress();
//            if (movies != null){
//                adapter.setMovieList(movies);
//            }
//        }
    }

}
