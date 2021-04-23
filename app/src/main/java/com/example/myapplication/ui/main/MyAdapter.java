package com.example.myapplication.ui.main;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.Team1;
import com.example.myapplication.Team2;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    public MyAdapter(Context context,@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        myContext = context;
        this.totalTabs = behavior;

    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                Team1 t=new Team1();
                return t;
            case 1:
                Team2 t1=new Team2();
                return t1;
            default:
                return null;
        }
    }
}
