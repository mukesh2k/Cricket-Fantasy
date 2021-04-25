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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.listpoints, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rcview78);
        ArrayList<Integer> key=new ArrayList<>();
        ArrayList<PointInfo>S=new ArrayList<>();
        int i;
        S.add(new PointInfo("hey","544","akdhfks","sdkfj","fdskhfh"));
        for(i=0;i<S.size();i++)key.add(i);
        new RecyclerViewPoints().setConfig( recyclerView, getContext(),S, key);
        return rootView;
}
}
