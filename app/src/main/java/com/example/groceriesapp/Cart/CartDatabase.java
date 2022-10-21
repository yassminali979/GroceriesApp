package com.example.groceriesapp.Cart;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {cart.class},version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase instance;


    public abstract CartDao Deo();
    public static CartDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),CartDatabase.class,"cart_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
