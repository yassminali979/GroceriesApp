package com.example.groceriesapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groceriesapp.Activity.Login;
import com.example.groceriesapp.Activity.StartActivity;
import com.example.groceriesapp.R;
import com.example.groceriesapp.SaveData;

public class AccountFragment extends Fragment {
    TextView accountName,accountEmail;
    Button logOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_account_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        accountEmail=getActivity().findViewById(R.id.accountEmail);
        accountName=getActivity().findViewById(R.id.accountName);
        logOut=getActivity().findViewById(R.id.logOut);
        SharedPreferences sh = getContext().getSharedPreferences("SharedPref", MODE_PRIVATE);
        String email = sh.getString("userEmail", "");
        accountEmail.setText(email);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getContext().getSharedPreferences("myData", MODE_PRIVATE);
                SharedPreferences.Editor editor=sh.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
    }
}
