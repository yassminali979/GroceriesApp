package com.example.groceriesapp.client;

import com.example.groceriesapp.Interface.ProductInterface;
import com.example.groceriesapp.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductClient {
    private static final String BASE_URL="https://fakestoreapi.com/products/";
    private ProductInterface productInterface;
    private static ProductClient productClient;

    public ProductClient() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productInterface=retrofit.create(ProductInterface.class);
    }
    public static ProductClient getInstance()
    {
        if(productClient==null)
        {
            productClient=new ProductClient();
        }
        return productClient;
    }
    public Call<ArrayList<String>> getAllProducts()
    {
        return productInterface.getAllProducts();
    }
    public Call<ArrayList<Product>> getAllCategoriesDetails(String name)
    {
        return productInterface.getAllJewelery(name);
    }
}
