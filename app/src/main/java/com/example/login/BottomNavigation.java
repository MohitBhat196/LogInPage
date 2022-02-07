package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


        BottomNavigationView btmNav = findViewById(R.id.bottomNavigation);
        btmNav.setSelectedItemId(R.id.bottomNavigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new FirstFragment()).commit();

        btmNav.setSelectedItemId(R.id.bottomNavigation);
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.maleNavigation:
                        fragment = new FirstFragment();
                        break;
                    case R.id.femaleNavigation:
                        fragment = new SecondFragment();
                        break;
                    case R.id.userNavigation:
                        fragment = new ThirdFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
    }
}