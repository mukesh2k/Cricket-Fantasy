package com.example.myapplication;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter1 extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    static PlayerScene t;
    static BallScene t1;
    public MyAdapter1(Context context, @NonNull FragmentManager fm, int behavior) {
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
                 t=new PlayerScene();
                return t;
            case 1:
                t1=new BallScene();
                return t1;
            default:
                return null;
        }
    }

}
