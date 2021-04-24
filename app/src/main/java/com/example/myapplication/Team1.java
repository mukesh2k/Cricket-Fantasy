package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.myapplication.TeamSelection.rl;


public class Team1 extends Fragment {
    public static ArrayList<team> t1 = new ArrayList<>();
    public static ArrayList<team> t2 = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.listteam1, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rcview);
        ArrayList<Integer> t2 = new ArrayList <Integer>( Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        new RecyclerViewPlayers().setConfig( recyclerView,getContext() ,t1, t2);
        return rootView;
    }

}



