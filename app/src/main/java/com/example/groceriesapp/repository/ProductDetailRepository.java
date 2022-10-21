package com.example.groceriesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.groceriesapp.Product;
import com.example.groceriesapp.client.LimitProductClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailRepository{
        private final MutableLiveData<Product> productDetailResponse=new MutableLiveData<>();
        private final MutableLiveData<String> errorPost=new MutableLiveData<>();
        public LiveData<String> error() {
            return errorPost;
        }
        public LiveData<Product>ProductDetailPosts() {
            return productDetailResponse;
        }
        public void getAllPostsDetailsFromServer(int id)
        {
            LimitProductClient.getInstance().DetailProducts(id).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if(response.isSuccessful())
                        {
                            productDetailResponse.setValue(response.body());
                        }
                        else
                        {
                            errorPost.setValue(response.errorBody().toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        errorPost.setValue(t.getMessage());
                    }
            });
        }
}