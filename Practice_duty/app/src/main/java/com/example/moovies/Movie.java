package com.example.moovies;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("year")
    private int year;
    @SerializedName("name")
    private String name;
    @SerializedName("poster")
    private Poster poster;
    @SerializedName("rating")
    private Rating rating;



}
