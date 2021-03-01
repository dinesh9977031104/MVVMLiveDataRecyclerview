package com.example.mvvmlivedatarecyclerview.network;

import com.example.mvvmlivedatarecyclerview.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIService {

    @GET("volley_array.json")
    Call<List<MovieModel>> getMovieList();

}
