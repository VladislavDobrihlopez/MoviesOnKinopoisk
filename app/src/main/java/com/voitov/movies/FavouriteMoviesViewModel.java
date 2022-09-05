package com.voitov.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavouriteMoviesViewModel extends AndroidViewModel {
    private MoviesDao moviesDao;

    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        moviesDao = MovieDatabase.getInstance(application).getMoviesDao();
    }

    public LiveData<List<Movie>> getMovies() {
        return moviesDao.getMovies();
    }
}
