package com.example.groceriesapp.Fav;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Fav.class},version = 1)
public abstract class FavDatabase extends RoomDatabase {
    private static FavDatabase instance;

    public abstract FavDao Deo();
    public static FavDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(), FavDatabase.class,"Fav_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
