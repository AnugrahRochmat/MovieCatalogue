package io.github.anugrahrochmat.moviecatalogue.ui.movietabs;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.BuildConfig;
import io.github.anugrahrochmat.moviecatalogue.ui.MainActivity;
import io.github.anugrahrochmat.moviecatalogue.data.models.movie.Movie;
import io.github.anugrahrochmat.moviecatalogue.data.models.movie.MoviesListResponse;
import io.github.anugrahrochmat.moviecatalogue.data.rest.ApiClient;
import io.github.anugrahrochmat.moviecatalogue.data.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Response;

public class MoviesListInteractor extends AsyncTask<Void, Void, List<Movie>> {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String TAG = MainActivity.class.getSimpleName();

    private String sortBy;
    private MoviesListPresenter moviesListPresenter;

    public MoviesListInteractor(String sortBy, MoviesListPresenter moviesListPresenter) {
        this.sortBy = sortBy;
        this.moviesListPresenter = moviesListPresenter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        moviesListPresenter.onProcessStart();
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesListResponse> call = apiService.getMoviesList(sortBy, API_KEY);

        try {
            Response<MoviesListResponse> response = call.execute();
            List<Movie> movies = response.body().getResults();
            return movies;
        } catch (Exception e){
            Log.e(TAG, "A problem occured ", e);
            moviesListPresenter.onErrorFetchMovie();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        moviesListPresenter.loadMovies(movies);
    }
}
