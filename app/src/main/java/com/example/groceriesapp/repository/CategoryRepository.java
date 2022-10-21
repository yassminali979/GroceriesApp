package com.example.groceriesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.client.CategoryClient;
import com.example.groceriesapp.client.ExploreClient;
import com.example.groceriesapp.client.ProductClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private final MutableLiveData<ArrayList<String>> categoryResponse = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Product>> responsePostDetails = new MutableLiveData<>();
    private final MutableLiveData<String> errorPost = new MutableLiveData<>();

    public LiveData<String> error() {
        return errorPost;
    }

    public LiveData<ArrayList<String>> CategoryPosts() {
        return categoryResponse;
    }
    public LiveData<ArrayList<Product>> getDetails() {
        return responsePostDetails;
    }
    public void getAllCategoriesFromServer() {
        ProductClient.getInstance().getAllProducts().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if (response.isSuccessful()) {
                    categoryResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });
    }
    public void getCategoriesDetails(String name)
    {
        CategoryClient.getInstance().getAllJewelery(name).enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful())
                {
                    responsePostDetails.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }
}
