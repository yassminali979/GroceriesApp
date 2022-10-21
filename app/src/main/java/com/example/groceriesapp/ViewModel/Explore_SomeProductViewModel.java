package com.example.groceriesapp.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.groceriesapp.Product;
import com.example.groceriesapp.repository.ExploreRepository;
import com.example.groceriesapp.repository.SomeProductRepository;

import java.util.ArrayList;
public class Explore_SomeProductViewModel extends ViewModel {
    private final ExploreRepository exploreRepository =new ExploreRepository();
    private final SomeProductRepository someProductRepository=new SomeProductRepository();
    public LiveData<ArrayList<Product>> explore_posts()
    {
        return exploreRepository.ExplorePosts();
    }
    public LiveData<String> error()
    {
        return exploreRepository.error();
    }
    public void getPostsFromRepo() {
        exploreRepository.getAllPostsFromServer();
    }
    public LiveData<ArrayList<Product>> somePosts()
    {
        return someProductRepository.SomePosts();
    }
    public LiveData<String> someError()
    {
        return someProductRepository.SomeError();
    }
    public void getSomePostsFromRepo()
    {
        someProductRepository.getSomeProductsFromServer();
    }
}
