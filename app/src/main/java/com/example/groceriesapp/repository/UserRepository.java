package com.example.groceriesapp.repository;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.groceriesapp.Activity.MainActivity;
import com.example.groceriesapp.Activity.Register;
import com.example.groceriesapp.UserResponseModel;
import com.example.groceriesapp.client.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class UserRepository {
    private final MutableLiveData<UserResponseModel> responsePost=new MutableLiveData<>();
    private final MutableLiveData<String> errorPost=new MutableLiveData<>();
    public LiveData<String> error() {
        return errorPost;
    }
    public LiveData<UserResponseModel>Posts() {
        return responsePost;
    }
    public void getLoginPostsFromServer(String name,String Email)
    {
        UserClient.getInstance().signup(name,Email).enqueue(new Callback<UserResponseModel>() {
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                if(response.isSuccessful())
                {
                    responsePost.setValue(response.body());
                }
                else
                {
                    errorPost.setValue(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {
                errorPost.setValue(t.getMessage());
            }
        });
    }
    public void getRegisterPostsFromServer(String name,String Email,String password)
    {
        UserClient.getInstance().signup(name,Email,password).enqueue(new Callback<UserResponseModel>() {
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                if (response.isSuccessful()) {
                    responsePost.setValue(response.body());
                } else {
                    errorPost.setValue(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {
                errorPost.setValue(t.getMessage());
            }
        });
    }
}