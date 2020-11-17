package com.example.roomexampleandroid.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.roomexampleandroid.entity.News;
import com.example.roomexampleandroid.entity.Source;
import com.example.roomexampleandroid.repository.DataBaseRepository;
import com.example.roomexampleandroid.repository.SourceRepository;

import java.util.List;

public class SourceViewModel extends AndroidViewModel {
    private MutableLiveData<News> mutableLiveData;
    private SourceRepository sourceRepository;
    private DataBaseRepository dataBaseRepository;
    private LiveData<List<Source>> allSources; //for db

    public SourceViewModel(@NonNull Application application) {
        super(application);
        sourceRepository = SourceRepository.getInstance();
        dataBaseRepository =DataBaseRepository.getInstance(application); //for db
        allSources = dataBaseRepository.getAllSourcestoDb();
    }

    public void init(String apiKey) {
        mutableLiveData = sourceRepository.getNews(apiKey);
    }

    public LiveData<News> getNewsRepository() {
        return mutableLiveData;
    }
    public LiveData<List<Source>> getAllSources() {
        return allSources;
    }
}
