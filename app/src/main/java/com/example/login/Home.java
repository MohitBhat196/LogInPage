package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    Toolbar myToolbar;


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2 ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menuBottomNavigation:
                Intent intent = new Intent(getApplicationContext(), BottomNavigation.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.viewPager);

        FragmentManager fManager = getSupportFragmentManager();
        adapter = new FragmentAdapter(fManager, getLifecycle());
        pager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("MALE"));
        tabLayout.addTab(tabLayout.newTab().setText("FEMALE"));
        tabLayout.addTab(tabLayout.newTab().setText("USER"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                    pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}