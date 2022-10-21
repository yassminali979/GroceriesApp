package com.example.groceriesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.client.LimitProductClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SomeProductRepository {
    private final MutableLiveData<ArrayList<Product>> someProductResponse=new MutableLiveData<>();
    private final MutableLiveData<String> errorPost=new MutableLiveData<>();
    public LiveData<String> SomeError() {
        return errorPost;
    }
    public LiveData<ArrayList<Product>>SomePosts() {
        return someProductResponse;
    }
    public void getSomeProductsFromServer()
    {
        LimitProductClient.getInstance().getSomeProducts().enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful())
                {
                    someProductResponse.setValue(response.body());
                }
                else
                {
                    errorPost.setValue(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                errorPost.setValue(t.getMessage());
            }
        });
    }
}
