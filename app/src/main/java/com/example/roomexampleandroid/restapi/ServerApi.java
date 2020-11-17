package com.example.roomexampleandroid.restapi;


import com.example.roomexampleandroid.entity.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {
    @GET("sources")
    Call<News> getNews(
            @Query("apiKey") String apiKey
    );
}
