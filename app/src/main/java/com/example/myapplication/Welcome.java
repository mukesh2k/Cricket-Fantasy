package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Welcome extends AppCompatActivity {
    public static String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        String a=getIntent().getStringExtra("key");
        id=a;
        TextView tc=findViewById(R.id.kz);
        tc.setText(a);
        Button game=findViewById(R.id.button);
        Button rec=findViewById(R.id.button2);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MatchArrayDev.class);
                i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity( i);
            }
        });
        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
