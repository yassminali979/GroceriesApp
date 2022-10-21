package com.example.groceriesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.groceriesapp.Fav.Fav;
import com.example.groceriesapp.Fav.FavDatabase;

import java.util.List;

public class FavRepository {
    private final MutableLiveData<List<Fav>> favouriteResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorPost = new MutableLiveData<>();

    public LiveData<String> error() {
        return errorPost;
    }

    public LiveData<List<Fav>> FavPosts() {
        return favouriteResponse;
    }

    public void getDataFromDataBase(FavDatabase favDatabase)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                favouriteResponse.postValue(favDatabase.Deo().getFavProducts());
            }
        }).start();
    }
}
