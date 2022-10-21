package com.example.groceriesapp.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.repository.CategoryRepository;
import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {
    private final CategoryRepository categoryRepository = new CategoryRepository();
    public LiveData<ArrayList<String>> posts() {
        return categoryRepository.CategoryPosts();
    }
    public LiveData<ArrayList<Product>> getDetails() {
        return categoryRepository.getDetails();
    }
    public LiveData<String> error() {
        return categoryRepository.error();
    }

    public void getCategoriesFromRepo() {
        categoryRepository.getAllCategoriesFromServer();
    }
    public void getCategoriesDetailsFromRepo(String name)
    {
        categoryRepository.getCategoriesDetails(name);
    }

}