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

        matches.add(new MatchArray("sfkdja","fdsjka","sajfdk","ksfdahkjf","jkfsdahj","sjafdh","fsadkhfds"));
        key.add("sjadfhfksda");


       // new RecyclerView_Config().setConfig( recyclerView, MatchArrayDev.this, FirebaseMatchHelper.matches, FirebaseMatchHelper.key);
     //fun();
    }

    void fun()
    {
        for (int k=0;k<req.size();k++)
// Request a string response from the provided URL.
        {

            int finalK = k;
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, req.get(k),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Object obj = null;
                            try {
                                obj = new JSONParser().parse(req.get(finalK));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            // typecasting obj to JSONObject
                            JSONObject jsonObject = (JSONObject) obj;
                            JSONObject jo = (JSONObject) jsonObject.get("info");
                            String city = (String) jo.get("venue") + " ," + (String) jo.get("city");
                            String comp = (String) jo.get("competition");
                            JSONArray dat = (JSONArray) jo.get("dates");
                            System.out.println(city + comp);
                            Iterator iterator = dat.iterator();
                            String s[] = new String[3]; // date and time
                            int i = 0;
                            while (iterator.hasNext()) {
                                s[i++] = (String) iterator.next();
                            }
                            JSONArray te = (JSONArray) jo.get("teams");
                            iterator = te.iterator();// teams
                            while (iterator.hasNext()) {
                                s[i++] = (String) iterator.next();
                            }
                            Toast.makeText(getApplicationContext(), comp, Toast.LENGTH_SHORT).show();
                            MatchArray m = new MatchArray(key.get(finalK), city, comp, s[1], s[2], s[0].split("T")[0], s[0].split("T")[1].substring(0, 5));
                            matches.add(m);
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    return;
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);
        }

    }




}
