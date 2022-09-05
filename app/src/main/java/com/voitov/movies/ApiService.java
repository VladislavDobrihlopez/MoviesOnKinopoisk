package com.voitov.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=" + BuildConfig.API_KEY + "&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    public Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=" + BuildConfig.API_KEY + "&field=id")
    public Single<TrailerResponse> loadTrailers(@Query("search") int movieId);

    @GET("review?token=" + BuildConfig.API_KEY + "&field=movieId&limit=10")
    public Single<ReviewResponse> loadReviews(@Query("search") int movieId);
}
