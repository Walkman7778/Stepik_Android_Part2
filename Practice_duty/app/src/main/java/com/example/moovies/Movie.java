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


    public Movie(int id, String description, int year, String name, Poster poster, Rating rating) {
        this.id = id;
        this.description = description;
        this.year = year;
        this.name = name;
        this.poster = poster;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
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
                ", description='" + description + '\'' +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
