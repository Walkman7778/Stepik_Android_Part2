package com.example.moovies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {


    @GET("v1.4/movie?token=RQ2NZWV-WW3MRB4-Q5YAGB6-XNF3H5T&year=2023&rating=%207,%2010&SortType=-1")
    Single<MovieResponse> loadMovies();
}
