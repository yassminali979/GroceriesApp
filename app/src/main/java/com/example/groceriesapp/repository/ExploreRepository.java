package com.example.groceriesapp.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.groceriesapp.client.ExploreClient;
import com.example.groceriesapp.Product;
import com.example.groceriesapp.client.LimitProductClient;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreRepository {
    private final MutableLiveData<ArrayList<Product>> exploreResponse=new MutableLiveData<>();
    private final MutableLiveData<String> errorPost=new MutableLiveData<>();
    public LiveData<String> error() {
        return errorPost;
    }
    public LiveData<ArrayList<Product>>ExplorePosts() {
        return exploreResponse;
    }
    public void getAllPostsFromServer()
    {
        ExploreClient.getInstance().ExploreProducts().enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful())
                {
                    exploreResponse.setValue(response.body());
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
