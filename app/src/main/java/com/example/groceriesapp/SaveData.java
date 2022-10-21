package com.example.groceriesapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData {
    SharedPreferences sharedPreferences;

    public SaveData(Context ctx) {
        sharedPreferences=ctx.getSharedPreferences("myData",Context.MODE_PRIVATE);
    }
    public void updateUserStatus(boolean status)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("status",status);
        editor.apply();
    }
    public boolean getUserStatus()
    {
        return sharedPreferences.getBoolean("status",false);
    }
}
