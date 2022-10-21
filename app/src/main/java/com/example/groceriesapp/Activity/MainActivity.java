package com.example.groceriesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.groceriesapp.R;
import com.example.groceriesapp.fragment.AccountFragment;
import com.example.groceriesapp.fragment.CartFragment;
import com.example.groceriesapp.fragment.ExploreFragment;
import com.example.groceriesapp.fragment.FavouriteFragment;
import com.example.groceriesapp.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new ShopFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.shop);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId())
                {
                    case R.id.shop:
                        fragment= new ShopFragment();
                        break;
                    case R.id.explore:
                        fragment= new ExploreFragment();
                        break;
                    case R.id.Cart:
                        fragment=new CartFragment();
                        break;
                    case R.id.Favourite:
                        fragment= new FavouriteFragment();
                        break;
                    case R.id.Account:
                        fragment= new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();

                return true;
            }
        });
    }

}