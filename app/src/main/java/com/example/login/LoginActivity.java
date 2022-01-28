package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHandler db;
    SharedPreferences sharedPreferences;
    CheckBox stayLoggedIn;
    boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("LOGIN");

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button signUp = findViewById(R.id.signUpButton);
        Button logIn = findViewById(R.id.loginButton);
        stayLoggedIn = findViewById(R.id.stayLoggedInButton);
        db = new DBHandler(this);

        sharedPreferences = getSharedPreferences("SHARED_PREFER", MODE_PRIVATE);

        isLoggedIn = sharedPreferences.getBoolean("CHECK", false);


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                boolean checked = stayLoggedIn.isChecked();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("EMAIL", emailValue);
                editor.putString("PASS", passwordValue);
                editor.putBoolean("CHECK", checked);

                editor.apply();


                Intent i = new Intent(LoginActivity.this, Home.class);
                startActivity(i);



            }
        });

        if(isLoggedIn == true){
            Toast.makeText(LoginActivity.this, "LOGIN DETAILS SAVED", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, Home.class);
            startActivity(i);
        }



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(myIntent);
            }
        });

    }
}