package com.example.groceriesapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groceriesapp.R;
import com.example.groceriesapp.SaveData;
import com.example.groceriesapp.UserResponseModel;
import com.example.groceriesapp.ViewModel.UserViewModel;

public class Login extends AppCompatActivity {
    EditText userEmail,userPassword;
    Button userLogin;
    TextView userCreateAccount;
    AlertDialog alertDialog;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail=findViewById(R.id.Email);
        userPassword=findViewById(R.id.userpassword);
        userLogin=findViewById(R.id.login);
        userCreateAccount=findViewById(R.id.have);
        AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
        builder.setMessage("Log in Your Account");
        builder.setTitle("Please wait");
        builder.setCancelable(false);
        alertDialog=builder.create();
        userViewModel =new ViewModelProvider(this).get(UserViewModel.class);
        if(new SaveData(Login.this).getUserStatus())
        {
            startActivity(new Intent(Login.this, MainActivity.class));
        }
        userCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEmail.getText().toString().isEmpty()||userPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(Login.this, "Check Your Data..?!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    alertDialog.show();
                    userViewModel.getUserLoginFromRepo(userEmail.getText().toString(),userPassword.getText().toString());
                    login();
                    SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("userEmail", userEmail.getText().toString());
                    myEdit.commit();
                }
            }
        });
    }
    public void login()
    {
        userViewModel.posts().observe(this, new Observer<UserResponseModel>() {
            @Override
            public void onChanged(UserResponseModel userResponseModel) {
                alertDialog.dismiss();
                if(userResponseModel.getResponse().equals("تأكد من البريد او الرقم السرى"))
                {
                    Toast.makeText(Login.this, userResponseModel.getResponse(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this,userResponseModel.getResponse(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    new SaveData(Login.this).updateUserStatus(true);
                }
            }
        });
        userViewModel.error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty())
                {
                    Log.d("Main","post yasmin:"+s);
                }
            }
        });
    }
}
