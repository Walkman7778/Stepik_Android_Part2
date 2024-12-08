package com.example.moovies;

import androidx.annotation.AttrRes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("docs")
    private List<Movie> movieResponse;


    public MovieResponse(List<Movie> movieResponse) {
        this.movieResponse = movieResponse;
    }

    public List<Movie> getMovieResponse() {
        return movieResponse;
    }

}
