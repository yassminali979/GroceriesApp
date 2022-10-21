package com.example.groceriesapp.client;

import com.example.groceriesapp.Interface.ProductInterface;
import com.example.groceriesapp.Product;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryClient {
    private static final String BASE_URL="https://fakestoreapi.com/";
    private ProductInterface productInterface;
    private static CategoryClient categoryClient;

    public CategoryClient() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productInterface=retrofit.create(ProductInterface.class);
    }
    public static CategoryClient getInstance()
    {
        if(categoryClient==null)
        {
            categoryClient=new CategoryClient();
        }
            return categoryClient;
    }
    public Call<ArrayList<Product>> getAllJewelery(String name)
    {
        return productInterface.getAllJewelery(name);
    }

}