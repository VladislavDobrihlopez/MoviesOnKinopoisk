package com.voitov.movies;

import com.google.gson.annotations.SerializedName;

public class TrailerResponse {
    @SerializedName("videos")
    private TrailersList trailersList;

    public TrailerResponse(TrailersList trailersList) {
        this.trailersList = trailersList;
    }

    public TrailersList getTrailerList() {
        return trailersList;
    }

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "trailersList=" + trailersList +
                '}';
    }
}
