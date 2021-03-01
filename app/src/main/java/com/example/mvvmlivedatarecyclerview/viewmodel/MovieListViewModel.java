package com.example.mvvmlivedatarecyclerview.viewmodel;

import android.util.Log;

import com.example.mvvmlivedatarecyclerview.model.MovieModel;
import com.example.mvvmlivedatarecyclerview.network.APIService;
import com.example.mvvmlivedatarecyclerview.network.RetroInstance;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movieList;

    public MovieListViewModel() {
        movieList = new MutableLiveData<>();
    }


    public MutableLiveData<List<MovieModel>> getMovieListObserver(){

        return movieList;
    }


    public void makeApiCall(){
        APIService apiService = RetroInstance.getRetrofitClient().create(APIService.class);
        Call<List<MovieModel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movieList.postValue(response.body());

                Log.d("result", "Success : " );
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                movieList.postValue(null);
                Log.d("result", "failed : " + t.getMessage());
            }
        });
    }
}
