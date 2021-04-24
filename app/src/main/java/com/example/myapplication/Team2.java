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
import java.util.Arrays;

public class Team2 extends Fragment {
    public static ArrayList<team> t2 = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listteam2, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rcview1);
        ArrayList<Integer> t22 = new ArrayList <Integer>( Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        new RecyclerViewPlayers2().setConfig( recyclerView,getContext() ,t2,t22);

        return rootView;
    }
}
