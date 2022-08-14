package com.voitov.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailsActivity";
    private static final String EXTRA_MOVIE = "movie";

    private MovieDetailsViewModel viewModel;

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private ImageView imageViewFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initializeViews();

        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Log.d(TAG, movie.toString());

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);

        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        TrailersAdapter trailersAdapter = new TrailersAdapter();
        trailersAdapter.setOnTrailerClickListener(new OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });

        recyclerViewTrailers.setAdapter(trailersAdapter);

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);

        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                Log.d(TAG, trailers.toString());
                trailersAdapter.setTrailers(trailers);
            }
        });

        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                Log.d(TAG, reviews.toString());
                reviewsAdapter.setReviews(reviews);
            }
        });

        viewModel.loadTrailers(movie.getId());
        viewModel.loadReviews(movie.getId());

        Drawable starOn = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_on);
        Drawable starOff = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_off);

        viewModel.getFavouriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie dbMovie) {
                if (dbMovie == null) {
                    //viewModel.saveMovie(movie);
                    imageViewFavourite.setImageDrawable(starOff);
                } else {
                    //viewModel.removeMovie(movie);
                    imageViewFavourite.setImageDrawable(starOn);
                }
            }
        });

        imageViewFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageViewFavourite.getDrawable().equals(starOn)) {
                    viewModel.removeMovie(movie);
                } else {
                    viewModel.saveMovie(movie);
                }
            }
        });
    }

    private void initializeViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewFavourite = findViewById(R.id.imageViewFavourite);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}