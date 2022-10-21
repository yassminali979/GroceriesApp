package com.example.groceriesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.groceriesapp.Cart.CartDatabase;
import com.example.groceriesapp.Cart.cart;
import java.util.List;

public class CartRepository {
    private final MutableLiveData<List<cart>> cartResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorPost = new MutableLiveData<>();

    public LiveData<String> error() {
        return errorPost;
    }

    public LiveData<List<cart>> CartPosts() {
        return cartResponse;
    }

    public void getDataFromDataBase(CartDatabase cartDatabase)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cartResponse.postValue(cartDatabase.Deo().getAllProducts());
            }
        }).start();
    }
}
