package com.example.groceriesapp.client;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.Interface.ProductInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LimitProductClient {
    private static final String BASE_URL="https://fakestoreapi.com/";
    private ProductInterface productInterface;
    private static LimitProductClient limitProductClient;

    public LimitProductClient() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productInterface=retrofit.create(ProductInterface.class);
    }
    public static LimitProductClient getInstance()
    {
        if(limitProductClient==null)
        {
            limitProductClient=new LimitProductClient();
        }
        return limitProductClient;
    }
    public Call<ArrayList<Product>> getSomeProducts()
    {
        return productInterface.getSomeProducts();
    }
    public Call<Product> DetailProducts(int id)
    {
        return productInterface.DetailProducts(id);
    }
}
