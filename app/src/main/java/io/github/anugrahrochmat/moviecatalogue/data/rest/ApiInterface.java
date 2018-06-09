package io.github.anugrahrochmat.moviecatalogue.data.rest;

import io.github.anugrahrochmat.moviecatalogue.data.models.movie.MoviesListResponse;
import io.github.anugrahrochmat.moviecatalogue.data.models.movie.MoviesSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search/movie")
    Call<MoviesSearchResponse> getFindMovies(@Query("api_key") String apiKey, @Query("query") String query );

    @GET("movie/{sort_by}")
    Call<MoviesListResponse> getMoviesList(@Path("sort_by") String sortBy, @Query("api_key") String apiKey);

}
