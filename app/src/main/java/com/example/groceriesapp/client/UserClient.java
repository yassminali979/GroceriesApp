package com.example.groceriesapp.client;
import com.example.groceriesapp.Interface.ProductInterface;
import com.example.groceriesapp.UserResponseModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClient {
    private static final String BASE_URL="https://bego8889.000webhostapp.com/php/";
    private ProductInterface productInterface;
    private static UserClient userClient;
    public UserClient()
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productInterface=retrofit.create(ProductInterface.class);
    }
    public static UserClient getInstance()
    {
        if(userClient==null)
        {
            userClient=new UserClient();
        }
        return userClient;
    }
    public Call<UserResponseModel>signup(String name, String email, String password)
    {
        return productInterface.signUp(name,email,password);
    }
    public Call<UserResponseModel>signup(String email,String password)
    {
        return productInterface.signUp(email,password);
    }
}
