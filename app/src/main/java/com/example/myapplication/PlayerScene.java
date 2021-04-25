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

import static com.example.myapplication.Gamee.S;
import static com.example.myapplication.Gamee.kzy;

public class PlayerScene extends Fragment {

   public static int y=0;

    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.listplayers, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rve);
        new RecyclerViewSelectedPlayer().setConfig( recyclerView, getContext(),S, kzy);
        return rootView;
    }

}
