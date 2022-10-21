package com.example.groceriesapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.groceriesapp.UserResponseModel;
import com.example.groceriesapp.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository = new UserRepository();
    public LiveData<UserResponseModel> posts() {
        return userRepository.Posts();
    }
    public LiveData<String> error() {
        return userRepository.error();
    }

    public void getUserLoginFromRepo(String name,String email) {

        userRepository.getLoginPostsFromServer(name,email);
    }
    public void getUserRegisterFromRepo(String name,String Email,String password)
    {
        userRepository.getRegisterPostsFromServer(name,Email,password);
    }
}