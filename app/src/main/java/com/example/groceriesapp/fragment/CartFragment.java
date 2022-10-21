package com.example.groceriesapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.groceriesapp.Activity.FinishActivity;
import com.example.groceriesapp.Fav.Fav;
import com.example.groceriesapp.R;
import com.example.groceriesapp.ViewModel.CartViewModel;
import com.example.groceriesapp.adapter.CartAdapter;
import com.example.groceriesapp.Cart.cart;
import com.example.groceriesapp.click.RemoveClick;
import java.util.List;

public class CartFragment extends Fragment implements RemoveClick {
    RecyclerView recyclerView;
    TextView text;
    Button PlaceOrder;
    CartAdapter cartAdapter=new CartAdapter(this);
    CartViewModel cartViewModel;
    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_cart_fragment, container, false);

    }
    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = getActivity().findViewById(R.id.CartRecycleView);
        text = getActivity().findViewById(R.id.noItems);
        PlaceOrder=getActivity().findViewById(R.id.placeOrder);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getData();
        cartViewModel.getDataFromRepo().observe(this, new Observer<List<cart>>() {
            @Override
            public void onChanged(List<cart> carts) {
                if (!carts.isEmpty()) {
                    text.setVisibility(View.GONE);
                    cartAdapter.setList(carts);
                    recyclerView.setAdapter(cartAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    text.setVisibility(View.VISIBLE);
                    cartAdapter.setList(carts);
                    recyclerView.setAdapter(cartAdapter);
                }
            }
        });
        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), FinishActivity.class));
            }
        });
    }
    @Override
    public void onClick(Long click) {
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CartDatabase.getInstance(getActivity()).Deo().deleteProduct(click);
                    cartViewModel.getData();
                }
            }).start();
            Toast.makeText(getContext(), "item deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getContext(), "error while delete", Toast.LENGTH_SHORT).show();
        }
    }

}
