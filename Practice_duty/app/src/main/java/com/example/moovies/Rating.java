package com.example.moovies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    private double rating1;

    public Rating(double kp) {
        this.rating1 = rating1;
    }

    public double getRating1() {
        return rating1;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rating='" + rating1 + '\'' +
                '}';
    }
}
