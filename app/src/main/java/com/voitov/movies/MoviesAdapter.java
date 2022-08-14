package com.voitov.movies;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private List<Movie> movies = new ArrayList<Movie>();
    private OnReachEndOfListListener onReachEndOfListListener;
    private OnMovieClickListener onMovieClickListener;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnReachEndOfListListener(OnReachEndOfListListener onReachEndOfListListener) {
        this.onReachEndOfListListener = onReachEndOfListListener;
    }

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );

        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Log.d("MoviesAdapter", "onBindViewHolder executed: " + position);
        Movie movie = movies.get(position);

        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);

        holder.textViewRating.setText(String.valueOf(movie.getRating().getKp()));

        double rating = movie.getRating().getKp();
        int backgroundResId;

        if (rating > 7) {
            backgroundResId = R.drawable.circle_green;
        } else if (rating > 5) {
            backgroundResId = R.drawable.circle_orange;
        } else {
            backgroundResId = R.drawable.circle_red;
        }

        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundResId);
        holder.textViewRating.setBackground(background);

        if (position >= getItemCount() - 8) {
            if (onReachEndOfListListener != null) {
                onReachEndOfListListener.onReachEnd();
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMovieClickListener != null) {
                    onMovieClickListener.onMovieClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
