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

public class PlayerScene extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.listplayers, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rve);
        ArrayList<Integer> t2 = new ArrayList <Integer>( Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        ArrayList<Integer>key=new ArrayList<>();
        ArrayList<SelectedPlayer>S=new ArrayList<>();
        int i;
        for(i=0;i<Team1.t1.size();i++)
        {
            if(!Team1.t1.get(i).getTit().equals("none"))
            {
                SelectedPlayer po=new SelectedPlayer(Team1.t1.get(i).getName(),Team1.tm,Team1.t1.get(i).getTit(),Team1.t1.get(i).getPoint().toString(),"0");
                S.add(po);
            }
            if(!Team2.t2.get(i).getTit().equals("none"))
            {
                SelectedPlayer po=new SelectedPlayer(Team2.t2.get(i).getName(),Team2.tm,Team2.t2.get(i).getTit(),Team2.t2.get(i).getPoint().toString(),"0");
                S.add(po);
            }
        }
        for(i=0;i<S.size();i++)key.add(i);
        new RecyclerViewSelectedPlayer().setConfig( recyclerView, getContext(),S, key);
        return rootView;
    }
}
