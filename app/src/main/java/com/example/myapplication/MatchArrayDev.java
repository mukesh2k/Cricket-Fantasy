package com.example.myapplication;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MatchArrayDev extends AppCompatActivity {
    static public ArrayList<MatchArray> matches=new ArrayList<MatchArray>();
    public static ArrayList<String> req=new ArrayList<String>();
    RecyclerView recyclerView;
    public static ArrayList<String> key=new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchselection);

        recyclerView =  findViewById(R.id.recyclerViewRDV);

        new FirebaseMatchHelper(getApplicationContext(),recyclerView).readMatches(new FirebaseMatchHelper.DataStatus() {
            @Override
            public void DataIsLoaded(ArrayList<String> Url, List<String> keys) {
            }});



    }





}
