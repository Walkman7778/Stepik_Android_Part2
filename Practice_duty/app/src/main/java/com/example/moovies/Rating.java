package com.example.moovies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    private String rating1;

    public Rating(String kp) {
        this.rating1 = rating1;
    }

    public String getRating1() {
        return rating1;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rating='" + rating1 + '\'' +
                '}';
    }
}
