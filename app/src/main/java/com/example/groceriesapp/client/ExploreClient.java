package com.example.groceriesapp.client;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.Interface.ProductInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExploreClient {
    private static final String BASE_URL="https://fakestoreapi.com/";
    private ProductInterface productInterface;
    private static ExploreClient exploreClient;

    public ExploreClient() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productInterface=retrofit.create(ProductInterface.class);
    }
    public static ExploreClient getInstance()
    {
        if(exploreClient==null)
        {
            exploreClient=new ExploreClient();
        }
        return exploreClient;
    }
    public Call<ArrayList<Product>> ExploreProducts()
    {
        return productInterface.ExploreProducts();
    }
}
