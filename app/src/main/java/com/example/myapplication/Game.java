package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {
    int score=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView teamvs= findViewById(R.id.tm);
        TextView batt= findViewById(R.id.batt);
        TextView live= findViewById(R.id.live);
        TextView over= findViewById(R.id.ov);
        Button bt=findViewById(R.id.balact);
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
        RecyclerView recyclerView = findViewById(R.id.ptlist);
        ArrayList<Integer>key=new ArrayList<>();
        for(i=0;i<S.size();i++)key.add(i);
        new RecyclerViewSelectedPlayer().setConfig( recyclerView, getApplicationContext(),S, key);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(com.example.myapplication.Game.this);
        View mView = getLayoutInflater().inflate(R.layout.listpoints, null);
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        RecyclerView recyclerView1 = findViewById(R.id.rcview78);
        ArrayList<Integer>key1=new ArrayList<>();
        ArrayList<PointInfo>S1=new ArrayList<>();
        int i1=0;
        S1.add(new PointInfo("hey","544","akdhfks","sdkfj","fdskhfh"));
        new RecyclerViewPoints().setConfig( recyclerView1, getApplicationContext(),S1, key1);
        for(i1=0;i<S.size();i++)key.add(i1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

            }
        });


    }
}
