package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FirebaseMatchHelper {
    static public ArrayList<MatchArray> matches=new ArrayList<MatchArray>();
    public static ArrayList<String> req=new ArrayList<String>();
    public static ArrayList<String> key=new ArrayList<String>();
    FirebaseDatabase database ;
    DatabaseReference databaseReference ;
    RecyclerView recyclerView;

    private ArrayList<String> Url=new ArrayList<>();

    private Context cont;
    public interface DataStatus {
        void DataIsLoaded(ArrayList<String> Url, List<String> keys);
    }

    public FirebaseMatchHelper(Context con,RecyclerView r) {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference( "matches");
        this.recyclerView=r;
        cont=con;
    }
    public void fun()
    {
        return;
    }

    public void readMatches( final DataStatus dataStatus){
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Url.clear();
                final int[] o = {0};

                for (DataSnapshot keyNode : dataSnapshot.getChildren())
                {


                    String patient = (String) keyNode.getValue();
                    Url.add( patient );
                    req.add(patient);


                    RequestQueue queue = Volley.newRequestQueue(cont);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET,  keyNode.getValue().toString(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    Object obj = null;
                                    key.add( (String) keyNode.getValue());
                                    try {
                                        // System.out.println(response);
                                        obj = new JSONParser().parse(response);
                                        JSONObject jsonObject = (JSONObject) obj;
                                        JSONObject jo = (JSONObject) jsonObject.get("info");
                                        if(jo==null)return;
                                        String city = (String) jo.get("venue") + " ," + (String) jo.get("city");
                                        String comp = (String) jo.get("competition");
                                        JSONArray dat = (JSONArray) jo.get("dates");
                                        System.out.println(city + comp);
                                        Iterator iterator = dat.iterator();
                                        String s[] = new String[3]; // date and time
                                        int i = 0;
                                        while (iterator.hasNext()) {
                                            s[i++] = (String) iterator.next();
                                            System.out.println(s[i-1]);
                                        }
                                        JSONArray te = (JSONArray) jo.get("teams");
                                        iterator = te.iterator();// teams
                                        while (iterator.hasNext()) {
                                            s[i++] = (String) iterator.next();
                                            System.out.println(s[i-1]);
                                        }
                                        // Toast.makeText(cont, comp, Toast.LENGTH_SHORT).show();
                                        MatchArray m=null;
                                        if(s.length<2)
                                            m = new MatchArray( response, city, comp, s[1], s[2], s[0].split("T")[0], s[0].split("T")[1].substring(0, 5));
                                        else  m = new MatchArray(response, city, comp, s[1], s[2], s[0].split("T")[0], "00:00");
                                        System.out.println(o[0]++);
                                        matches.add(m);
                                        new RecyclerView_Config().setConfig( recyclerView, cont, FirebaseMatchHelper.matches, FirebaseMatchHelper.key);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    // typecasting obj to JSONObject

                                }

                            },
                            new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            return;
                        }
                    });

// Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }


}