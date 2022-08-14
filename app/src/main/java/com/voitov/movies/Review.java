package com.voitov.movies;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("type")
    private String type;
    @SerializedName("author")
    private String author;
    @SerializedName("review")
    private String review;

    public Review(String type, String author, String review) {
        this.type = type;
        this.author = author;
        this.review = review;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "type=" + type +
                ", author='" + author + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
