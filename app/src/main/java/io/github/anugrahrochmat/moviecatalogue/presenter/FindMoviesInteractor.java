package io.github.anugrahrochmat.moviecatalogue.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import io.github.anugrahrochmat.moviecatalogue.BuildConfig;
import io.github.anugrahrochmat.moviecatalogue.activity.MainActivity;
import io.github.anugrahrochmat.moviecatalogue.model.Movie;
import io.github.anugrahrochmat.moviecatalogue.model.MoviesResponse;
import io.github.anugrahrochmat.moviecatalogue.rest.ApiClient;
import io.github.anugrahrochmat.moviecatalogue.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Response;

public class FindMoviesInteractor extends AsyncTask<Void, Void, List<Movie>> {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String TAG = MainActivity.class.getSimpleName();

    private String query;
    private FindMoviesPresenter findMoviesPresenter;

    public FindMoviesInteractor(String query, FindMoviesPresenter findMoviesPresenter) {
        this.query = query;
        this.findMoviesPresenter = findMoviesPresenter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        findMoviesPresenter.onProcessStart();
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getFindMovies(API_KEY, query);

        try {
            Response<MoviesResponse> response = call.execute();
            List<Movie> movies = response.body().getResults();
            return movies;
        } catch (Exception e){
            Log.e(TAG, "A problem occured ", e);
            findMoviesPresenter.onErrorFetchMovie();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        findMoviesPresenter.loadMovies(movies);
    }


}
