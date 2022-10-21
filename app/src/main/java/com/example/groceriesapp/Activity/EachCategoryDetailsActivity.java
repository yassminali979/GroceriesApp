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
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.R;
import com.example.groceriesapp.ViewModel.CategoryViewModel;
import com.example.groceriesapp.adapter.EachProductAdapter;
import com.example.groceriesapp.adapter.SomeProductAdapter;
import com.example.groceriesapp.click.onClick;
import com.example.groceriesapp.client.CategoryClient;
import com.example.groceriesapp.client.ProductClient;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EachCategoryDetailsActivity extends AppCompatActivity implements onClick {
    EachProductAdapter eachProductAdapter;
    RecyclerView recyclerView;
    CategoryViewModel categoryViewModel;
    TextView CategoryName;
    ImageView backArrow,sort;
    SearchView searchView;
    private List<Product> mProduct;
    ArrayList<Product> mProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_category_details);
        recyclerView = findViewById(R.id.eachCategoryRec);
        searchView=findViewById(R.id.searchViewCategoryDetail);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kitEachCategory);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        categoryViewModel=new ViewModelProvider(this).get(CategoryViewModel.class);
        eachProductAdapter = new EachProductAdapter(this,getApplicationContext());
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name = sh.getString("CategoryName", "");
//        CategoryName.setText(name);
        categoryViewModel.getCategoriesDetailsFromRepo(name);
        mProduct=new ArrayList<>();
        mProductList=new ArrayList<>();

        categoryViewModel.getDetails().observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                progressBar.setVisibility(View.GONE);
                eachProductAdapter.setItems(products);
                mProduct=products;
                mProductList=products;
                recyclerView.setAdapter(eachProductAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
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
//        backArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EachCategoryDetailsActivity.this,MainActivity.class));
//            }
//        });
//        sort.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Collections.sort(mProduct,Product.productComparator);
//                eachProductAdapter.setItems((ArrayList<Product>) mProduct);
//                recyclerView.setAdapter(eachProductAdapter);
//                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//            }
//        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }
    @Override
    public void GetAllProductDetails(Product product) {
        Intent intent=new Intent(EachCategoryDetailsActivity.this, ProductDetailActivity.class);
        intent.putExtra("id",product.getId());
        intent.putExtra("name",product.getTitle());
        intent.putExtra("image",product.getImage());
        startActivity(intent);
    }

    @Override
    public void GetAllCategoryDetails() {
    }
    private void filter(String text)
    {
        ArrayList<Product> filteredList=new ArrayList<>();
        for(Product item:mProductList)
        {
            if(item.getTitle().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        eachProductAdapter=new EachProductAdapter(this,getApplicationContext());
        recyclerView.setAdapter(eachProductAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        eachProductAdapter.setItems(filteredList);
    }
}