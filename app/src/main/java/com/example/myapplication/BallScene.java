package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BallScene extends Fragment {
    public static ArrayList<Integer> key=new ArrayList<>();
    public static ArrayList<PointInfo>S=new ArrayList<>();
    public static int u=0;
    RecyclerView recyclerView;
    RecyclerViewPoints r;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.listpoints, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rcview78);
        int i;
        for(i=0;i<S.size();i++)key.add(i);
        r= new RecyclerViewPoints();
        r.setConfig( recyclerView, getContext(),S, key);
        return rootView;
}

}
