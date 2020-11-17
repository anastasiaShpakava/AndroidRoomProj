package com.example.roomexampleandroid.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.roomexampleandroid.entity.News;
import com.example.roomexampleandroid.restapi.RestClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceRepository {
    private static SourceRepository sourceRepository;

    public static SourceRepository getInstance() {
        if (sourceRepository == null) {
            sourceRepository = new SourceRepository();
        }
        return sourceRepository;
    }

    private RestClient restClient = RestClient.getInstance();

    public MutableLiveData<News> getNews(String key) {//отправка данных в LiveData
        restClient.startRetrofit();
        final MutableLiveData<News> newsData = new MutableLiveData<>();
        restClient.getServerApi().getNews(key).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body()); //помещаем значение в LiveData
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("error", "can't parse data: ", t);
            }
        });
        return newsData;
    }
}
