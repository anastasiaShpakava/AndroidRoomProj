package com.example.roomexampleandroid.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomexampleandroid.entity.Source;

import java.util.List;

@Dao
public interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Source> sources);

    @Query("SELECT * FROM Source")
    LiveData<List<Source>> getSources();
}
