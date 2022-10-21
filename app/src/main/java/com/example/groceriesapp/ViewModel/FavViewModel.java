package com.example.groceriesapp.ViewModel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.groceriesapp.Fav.Fav;
import com.example.groceriesapp.Fav.FavDatabase;
import com.example.groceriesapp.repository.FavRepository;

import java.util.List;

public class FavViewModel extends AndroidViewModel {
    public static FavDatabase favDatabase;
    private FavRepository favRepository=new FavRepository();
    public FavViewModel(@NonNull Application application)
    {
        super(application);
        favDatabase= Room.databaseBuilder(application.getApplicationContext(),FavDatabase.class,"Fav_database").fallbackToDestructiveMigration().build();
    }
    public LiveData<List<Fav>> getDataFromRepo() {
        return favRepository.FavPosts();
    }
    public void getData()
    {
        favRepository.getDataFromDataBase(favDatabase);
    }

}
