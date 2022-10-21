package com.example.groceriesapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.repository.CategoryRepository;
import com.example.groceriesapp.repository.ProductDetailRepository;

import java.util.ArrayList;

public class ProductDetailViewModel extends ViewModel {
    private final ProductDetailRepository productDetailRepository = new ProductDetailRepository();

    public LiveData<Product> posts() {
        return productDetailRepository.ProductDetailPosts();
    }

    public LiveData<String> error() {
        return productDetailRepository.error();
    }

    public void getAllPostsDetailsFromRepo(int id) {
        productDetailRepository.getAllPostsDetailsFromServer(id);
    }
}
