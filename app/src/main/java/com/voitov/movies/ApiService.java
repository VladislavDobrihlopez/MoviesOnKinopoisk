package com.voitov.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    //@GET("movie?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=rating.kp&search=4-10&sortField=votes.kp&sortType=-1&limit=100")
    public Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=id")
    public Single<TrailerResponse> loadTrailers(@Query("search") int movieId);

    @GET("review?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=movieId&limit=10")
    public Single<ReviewResponse> loadReviews(@Query("search") int movieId);
}
