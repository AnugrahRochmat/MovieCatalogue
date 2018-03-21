package io.github.anugrahrochmat.moviecatalogue.rest;

import io.github.anugrahrochmat.moviecatalogue.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search/movie")
    Call<MoviesResponse> getFindMovies(@Query("api_key") String apiKey);

}
