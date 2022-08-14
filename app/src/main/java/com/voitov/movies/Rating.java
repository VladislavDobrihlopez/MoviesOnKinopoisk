package com.voitov.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private double kp;
    @SerializedName("imdb")
    private double imdb;

    public Rating(double kp, double imdb) {
        this.kp = kp;
        this.imdb = imdb;
    }

    public double getKp() {
        return kp;
    }

    public double getImdb() {
        return imdb;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp=" + kp +
                ", imdb=" + imdb +
                '}';
    }
}
