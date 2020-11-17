package com.example.roomexampleandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomexampleandroid.dao.SourceDao;
import com.example.roomexampleandroid.entity.Source;


@Database(entities = {Source.class}, exportSchema = false, version = 2)
public abstract class SourceDatabase extends RoomDatabase {

    private static final String DB_NAME = "source.db";
    public abstract SourceDao sourceDao();
    private static SourceDatabase instance;

    public static SourceDatabase getInstance(Context context) {
        if (instance == null) {
            instance =buildDatabaseInstance(context);
        }
        return instance;
    }
    private static SourceDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                SourceDatabase.class,
                DB_NAME).build();
    }
}
