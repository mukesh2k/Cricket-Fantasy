package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Gamee extends AppCompatActivity {
    public static Context con;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamee);
        TabLayout tabs = findViewById(R.id.tab1);
        tabs.addTab(tabs.newTab().setText("Team 1"));
        tabs.addTab(tabs.newTab().setText("Team 2"));
        con=getApplicationContext();
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPager viewPager = findViewById(R.id.view_pager1);
        tabs.setupWithViewPager(viewPager);
        final MyAdapter1 adapter = new MyAdapter1(this, getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


    }
}

