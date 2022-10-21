package com.example.groceriesapp.fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceriesapp.ViewModel.Explore_SomeProductViewModel;
import com.example.groceriesapp.Product;
import com.example.groceriesapp.Activity.ProductDetailActivity;
import com.example.groceriesapp.R;
import com.example.groceriesapp.adapter.ExploreAdapter;
import com.example.groceriesapp.click.onClick;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.ArrayList;

public class ExploreFragment extends Fragment implements onClick {
    RecyclerView recyclerView;
    ExploreAdapter exploreAdapter;
    Explore_SomeProductViewModel exploreSomeProductViewModel;
    SearchView searchView;
    ArrayList<Product> mProductList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_explore_fragment, container, false);
    }
    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView=getActivity().findViewById(R.id.exploreList);
        searchView=getActivity().findViewById(R.id.searchViewExplore);
        exploreAdapter=new ExploreAdapter(this);
        ProgressBar progressBar = (ProgressBar)getActivity().findViewById(R.id.spin_kitExplore);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        exploreSomeProductViewModel =new ViewModelProvider(this).get(Explore_SomeProductViewModel.class);
        exploreSomeProductViewModel.getPostsFromRepo();
        mProductList=new ArrayList<>();
        exploreSomeProductViewModel.explore_posts().observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                progressBar.setVisibility(View.GONE);
                if(!products.isEmpty())
                {
                    Log.d("Main","post yasmin:"+products.get(1));
                    exploreAdapter.setItems(products);
                    mProductList=products;
                    recyclerView.setAdapter(exploreAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                }
            }
        });
        exploreSomeProductViewModel.error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty())
                {
                    Log.d("Main","post yasmin:"+s);
                }
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
        Intent intent=new Intent(getActivity(), ProductDetailActivity.class);
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
        exploreAdapter=new ExploreAdapter(this);
        recyclerView.setAdapter(exploreAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        exploreAdapter.setItems(filteredList);
    }

}