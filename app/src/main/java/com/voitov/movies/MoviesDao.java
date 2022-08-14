package com.voitov.movies;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM favourite_movies")
    public LiveData<List<Movie>> getMovies();

    @Query("SELECT * FROM favourite_movies WHERE id=:movieId")
    public LiveData<Movie> getFavouriteMovie(int movieId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Completable saveMovie(Movie movie);

    @Query("DELETE FROM favourite_movies WHERE id=:movieId")
    public Completable removeMovie(int movieId);
}
