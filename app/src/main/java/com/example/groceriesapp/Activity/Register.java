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
import com.example.groceriesapp.UserResponseModel;
import com.example.groceriesapp.ViewModel.UserViewModel;

public class Register extends AppCompatActivity {
    EditText name,email,password;
    TextView AlreadyHave;
    Button createAccount;
    AlertDialog alertDialog;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.PersonName);
        email=findViewById(R.id.userEmail);
        password=findViewById(R.id.password);
        createAccount=findViewById(R.id.register);
        AlreadyHave=findViewById(R.id.already_hav);
        userViewModel =new ViewModelProvider(this).get(UserViewModel.class);
        AlertDialog.Builder builder=new AlertDialog.Builder(Register.this);
        builder.setMessage("Creating Account...");
        builder.setTitle("Please Wait");
        builder.setCancelable(false);
        alertDialog=builder.create();
        AlreadyHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()||email.getText().toString().isEmpty()||password.getText().toString().isEmpty())
                {
                    Toast.makeText(Register.this, "Check Your Data..?!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    alertDialog.show();
                    userViewModel.getUserRegisterFromRepo(name.getText().toString(),email.getText().toString(),password.getText().toString());
                    register();
                }
            }
        });
    }
    public void register()
    {
        userViewModel.posts().observe(this, new Observer<UserResponseModel>() {
            @Override
            public void onChanged(UserResponseModel userResponseModel) {
                alertDialog.dismiss();
                if(userResponseModel.getResponse().equals("هذا البريد مسجل من قبل"))
                {
                    Toast.makeText(Register.this, userResponseModel.getResponse(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Register.this, "", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));
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