package com.example.moovies;

import androidx.annotation.NonNull;

public class Poster {


    private String url;


    public Poster(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "Poster{" +
                "url='" + url + '\'' +
                '}';
    }
}
