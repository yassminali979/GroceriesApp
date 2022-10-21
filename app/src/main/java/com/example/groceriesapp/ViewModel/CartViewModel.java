package com.example.groceriesapp.ViewModel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.groceriesapp.Cart.CartDatabase;
import com.example.groceriesapp.Cart.cart;
import com.example.groceriesapp.repository.CartRepository;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    public static CartDatabase cartDatabase;
    private CartRepository cartRepository=new CartRepository();
    public CartViewModel(@NonNull Application application)
    {
        super(application);
        cartDatabase= Room.databaseBuilder(application.getApplicationContext(),CartDatabase.class,"cart_database").fallbackToDestructiveMigration().build();
    }
    public LiveData<List<cart>> getDataFromRepo() {
        return cartRepository.CartPosts();
    }
    public void getData()
    {
       cartRepository.getDataFromDataBase(cartDatabase);
    }
}
