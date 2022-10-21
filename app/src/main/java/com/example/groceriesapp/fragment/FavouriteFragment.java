package com.example.groceriesapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceriesapp.Cart.CartDatabase;
import com.example.groceriesapp.Fav.Fav;
import com.example.groceriesapp.Fav.FavDatabase;
import com.example.groceriesapp.R;
import com.example.groceriesapp.ViewModel.CartViewModel;
import com.example.groceriesapp.ViewModel.FavViewModel;
import com.example.groceriesapp.adapter.CartAdapter;
import com.example.groceriesapp.adapter.FavouriteAdapter;
import com.example.groceriesapp.Cart.cart;
import com.example.groceriesapp.click.RemoveClick;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements RemoveClick {
    RecyclerView recyclerView;
    FavouriteAdapter favouriteAdapter;
    FavViewModel favViewModel;
    TextView textNoFav;
    Button addToCart;
    CartViewModel cartViewModel;
    CartAdapter cartAdapter;
    CartFragment cartFragment;
    List<Fav> listFav;
    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_favourite_fragment, container, false);
    }
    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = getActivity().findViewById(R.id.FavRec);
        addToCart = getActivity().findViewById(R.id.add);
        textNoFav = getActivity().findViewById(R.id.noFavItems);
        listFav=new ArrayList<Fav>();
        cartAdapter=new CartAdapter(this);
        favouriteAdapter=new FavouriteAdapter(this,getContext());
        favViewModel = new ViewModelProvider(this).get(FavViewModel.class);
        cartViewModel=new ViewModelProvider(this).get(CartViewModel.class);
        favViewModel.getData();

        favViewModel.getDataFromRepo().observe(this, new Observer<List<Fav>>() {
            @Override
            public void onChanged(List<Fav> fav) {
                if (!fav.isEmpty()) {
                    textNoFav.setVisibility(View.GONE);
                    favouriteAdapter.setList(fav);
                    listFav=fav;
                    recyclerView.setAdapter(favouriteAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    textNoFav.setVisibility(View.VISIBLE);
                    favouriteAdapter.setList(fav);
                    recyclerView.setAdapter(favouriteAdapter);
                }
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("log","new"+listFav.size());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                            for(int i=0;i<listFav.size();i++)
                            {
                                Log.d("log","list"+listFav.get(1).getTitle());
                                if(!listFav.get(i).equals(""))
                                {

                                }
                            }
                    }
                }).start();
            }
        });
    }
    @Override
    public void onClick(Long click) {
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FavDatabase.getInstance(getActivity()).Deo().deleteProductWithId(click);
                    favViewModel.getData();
                }
            }).start();
            Toast.makeText(getContext(), "item deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getContext(), "error while delete", Toast.LENGTH_SHORT).show();
        }
    }

}
