package com.example.roomexampleandroid.repository;


import android.content.Context;
import android.util.Log;


import androidx.lifecycle.LiveData;
import com.example.roomexampleandroid.dao.SourceDao;
import com.example.roomexampleandroid.database.SourceDatabase;
import com.example.roomexampleandroid.entity.News;
import com.example.roomexampleandroid.entity.Source;
import com.example.roomexampleandroid.restapi.RestClient;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBaseRepository {
    private static DataBaseRepository  dataBaseRepository;
    private SourceDao sourceDao;
    private LiveData<List<Source>> allSourcestoDb;
    private Context context;

    public static DataBaseRepository getInstance(Context context) {
        if (dataBaseRepository == null) {
            dataBaseRepository = new DataBaseRepository(context);
        }
        return dataBaseRepository;
    }

    public DataBaseRepository(Context context) {
        this.context = context;
        SourceDatabase db = SourceDatabase.getInstance(context);
        sourceDao = db.sourceDao();
        allSourcestoDb = sourceDao.getSources();
    }

    public void getSourceListTodb(String key) {//отправка данных в LiveData
        RestClient restClient = RestClient.getInstance();
        restClient.startRetrofit();

        restClient.getServerApi().getNews(key).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                Completable.fromAction(new Action (){
                    @Override
                    public void run() {
                        if (response.body() != null) {

                            List<Source> list = response.body().getSources();
                            sourceDao.insert(list);
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("error", "Can't parse data " + t);
            }
        });
    }

    public LiveData<List<Source>> getAllSourcestoDb() {
        return allSourcestoDb;
    }
}