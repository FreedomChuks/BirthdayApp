package com.funworld.interfaces;


import android.content.Context;

import com.funworld.Model.Birthday;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Birthday.class},version = 1,exportSchema = false)
public abstract class BirthdayDB extends RoomDatabase {
    //use a singleton method to prevent createing class instance\
    private static BirthdayDB instance;
    public abstract BirthdayDao birthdayDao();

    public static synchronized BirthdayDB getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), BirthdayDB.class,"birthday_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
