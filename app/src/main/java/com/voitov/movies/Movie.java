package com.voitov.movies;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favourite_movies")
public class Movie implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("year")
    private int year;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;

    @Embedded
    @SerializedName("poster")
    private Poster poster;
    @Embedded
    @SerializedName("rating")
    private Rating rating;

    public Movie(int id, int year, String name, String description, Poster poster, Rating rating) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.description = description;
        this.poster = poster;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
