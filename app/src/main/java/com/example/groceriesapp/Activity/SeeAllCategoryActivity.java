package com.example.groceriesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.R;
import com.example.groceriesapp.ViewModel.CategoryViewModel;
import com.example.groceriesapp.adapter.SeeAllCategoryAdapter;
import com.example.groceriesapp.click.onClick;

import java.util.ArrayList;

public class SeeAllCategoryActivity extends AppCompatActivity implements onClick {
    RecyclerView recyclerView;
    SeeAllCategoryAdapter seeAllCategoryAdapter;
    ArrayList<String>products;
    CategoryViewModel categoryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_category);
        recyclerView=findViewById(R.id.seeAllCategotyRec);
        products=new ArrayList<>();
        seeAllCategoryAdapter=new SeeAllCategoryAdapter(this,getApplicationContext());
        categoryViewModel=new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategoriesFromRepo();
        categoryViewModel.posts().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                if(!strings.isEmpty())
                {
                    seeAllCategoryAdapter.setItems(strings);
                    recyclerView.setAdapter(seeAllCategoryAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                }
            }
        });
        categoryViewModel.error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty())
                {
                    Log.d("Main","post yasmin:"+s);
                }
            }
        });
    }
    @Override
    public void GetAllProductDetails(Product product) {
        Intent intent=new Intent(SeeAllCategoryActivity.this, EachCategoryDetailsActivity.class);
        intent.putExtra("id",product.getId());
        intent.putExtra("name",product.getTitle());
        intent.putExtra("image",product.getImage());
        startActivity(intent);
    }
    @Override
    public void GetAllCategoryDetails() {
        Intent intent=new Intent(SeeAllCategoryActivity.this, EachCategoryDetailsActivity.class);
        startActivity(intent);
    }

}