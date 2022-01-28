package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    SharedPreferences preferences;
    TextView emailValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        emailValue = findViewById(R.id.showEmail);
        Button logOut = findViewById(R.id.logOutButton);

        preferences = getSharedPreferences("SHARED_PREFER",MODE_PRIVATE);

        String mail = preferences.getString("EMAIL", "");
        emailValue.setText(mail);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent i = new Intent(Home.this, LoginActivity.class);
                startActivity(i);
            }
        });




    }
}