package com.example.moovies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {



    @GET("movie?token=RQ2NZWV-WW3MRB4-Q5YAGB6-XNF3H5T&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);







}
