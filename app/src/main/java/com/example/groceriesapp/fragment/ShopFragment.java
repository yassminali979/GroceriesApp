package com.example.groceriesapp.fragment;
import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.groceriesapp.Activity.EachCategoryDetailsActivity;
import com.example.groceriesapp.Activity.SeeAllCategoryActivity;
import com.example.groceriesapp.ViewModel.CategoryViewModel;
import com.example.groceriesapp.ViewModel.Explore_SomeProductViewModel;
import com.example.groceriesapp.Product;
import com.example.groceriesapp.Activity.ProductDetailActivity;
import com.example.groceriesapp.R;
import com.example.groceriesapp.adapter.CustomerAdapter;
import com.example.groceriesapp.adapter.EachProductAdapter;
import com.example.groceriesapp.adapter.SomeProductAdapter;
import com.example.groceriesapp.click.onClick;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements onClick {
    RecyclerView categoryRecyclerView,productsRecyclerView;
    CustomerAdapter customerAdapter;
    Explore_SomeProductViewModel exploreSomeProductViewModel;
    SomeProductAdapter someProductAdapter;
    CategoryViewModel categoryViewModel;
    TextView seeAll;
    EachProductAdapter eachProductAdapter;
    SearchView searchView;
    ArrayList<Product> mProductList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_shop_fragment, container, false);
    }
    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        categoryRecyclerView=getActivity().findViewById(R.id.recList1);
        productsRecyclerView=getActivity().findViewById(R.id.recList2);
        seeAll=getActivity().findViewById(R.id.seeAll);
        searchView=getActivity().findViewById(R.id.searchView);
        ProgressBar progressBar = (ProgressBar)getActivity().findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        eachProductAdapter=new EachProductAdapter(this,getContext());
        customerAdapter=new CustomerAdapter(this,getContext());
        someProductAdapter=new SomeProductAdapter(this);
        exploreSomeProductViewModel =new ViewModelProvider(this).get(Explore_SomeProductViewModel.class);
        categoryViewModel=new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategoriesFromRepo();
        mProductList=new ArrayList<>();
        categoryViewModel.error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty())
                {
                    Log.d("Main","post yasmin:"+s);
                }
            }
        });
        categoryViewModel.posts().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                if(!strings.isEmpty())
                {
                    customerAdapter.setItems(strings);
                    categoryRecyclerView.setAdapter(customerAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                    categoryRecyclerView.setLayoutManager(layoutManager);
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
        exploreSomeProductViewModel.getSomePostsFromRepo();
        exploreSomeProductViewModel.somePosts().observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                progressBar.setVisibility(View.GONE);
                if(!products.isEmpty())
                {
                    someProductAdapter.setItems(products);
                    mProductList=products;
                    productsRecyclerView.setAdapter(someProductAdapter);
                    productsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                }
            }
        });
        exploreSomeProductViewModel.someError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty())
                {
                    Log.d("Main","post yasmin:"+s);
                }
            }
        });
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),SeeAllCategoryActivity.class));
            }
        });
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
        Intent intent=new Intent(getContext(), ProductDetailActivity.class);
        intent.putExtra("id",product.getId());
        intent.putExtra("name",product.getTitle());
        intent.putExtra("image",product.getImage());
        startActivity(intent);
    }
    @Override
    public void GetAllCategoryDetails() {
        Intent intent=new Intent(getContext(), EachCategoryDetailsActivity.class);
        startActivity(intent);
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
        someProductAdapter=new SomeProductAdapter(this);
        productsRecyclerView.setAdapter(someProductAdapter);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        someProductAdapter.setItems(filteredList);
    }

}
