package com.example.groceriesapp.Interface;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.UserResponseModel;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductInterface {
    @GET("categories")
    Call<ArrayList<String>> getAllProducts();

    @GET("products?limit=5")
    Call<ArrayList<Product>> getSomeProducts();

    @GET("products")
    Call<ArrayList<Product>> ExploreProducts();

    @GET("products/{id}")
    Call<Product> DetailProducts(@Path("id") int id);

     @GET("products/category/{name}")
     Call<ArrayList<Product>> getAllJewelery(@Path("name") String name);

    @FormUrlEncoded
    @POST("signup.php")
    Call<UserResponseModel> signUp(@Field("name") String name, @Field("email") String Email, @Field("password") String password);
    @FormUrlEncoded
    @POST("login.php")
    Call<UserResponseModel> signUp(@Field("email") String Email,@Field("password") String password);
}
