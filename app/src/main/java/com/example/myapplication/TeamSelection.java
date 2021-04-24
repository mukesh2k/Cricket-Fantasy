package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;

public class TeamSelection extends AppCompatActivity {
    ArrayList<String> T1 = new ArrayList<>();
    ArrayList<String> T2 = new ArrayList<>();
    public static RadioGroup radioGroup ;
   public static Boolean vc=false,c=false;
   public  static TextView ty;
   public static RelativeLayout rl;
    public static double cur=0.0;
    public static Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamselection);
        String url = getIntent().getStringExtra("key");
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Team 1"));
        tabs.addTab(tabs.newTab().setText("Team 2"));

        radioGroup= findViewById(R.id.grp);
        RadioButton rd=findViewById(R.id.teambutton);
        ty=findViewById(R.id.current);
        rd.setChecked(true);
        con=getApplicationContext();
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setupWithViewPager(viewPager);
        final MyAdapter adapter = new MyAdapter(this, getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        System.out.println("URL =" + url);
        Button po=findViewById(R.id.next);

        po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<Team1.t1.size();i++)
                {
                    System.out.println(Team1.t1.get(i).getName()+Team1.t1.get(i).getPoint()+Team1.t1.get(i).getTit());
                }
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Object obj = null;
                        try {
                            obj = new JSONParser().parse(response);
                            JSONObject jsonObject = (JSONObject) obj;
                            JSONArray jo = (JSONArray) jsonObject.get("innings");


                            JSONObject JO = (JSONObject) jo.get(0);
                            // System.out.println(JO.toString());
                            System.out.println("");
                            JSONObject jo1 = (JSONObject) JO.get("1st innings");
                            JSONArray jo2 = (JSONArray) jo1.get("deliveries");
                            for (int k = 0, over = 0, bal = 1; k < jo2.size(); k++) {
                                JSONObject ball = (JSONObject) jo2.get(k);
                                System.out.println(jo2.get(k));
                                if (over > 20)
                                    break;
                                try {
                                    ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    // System.out.print(Integer.toString(over) + "." + Integer.toString(bal));
                                    System.out.println(ind.get("batsman"));
                                    if (T1.contains(ind.get("batsman")) == false)
                                        T1.add(ind.get("batsman").toString());
                                    if (T1.contains(ind.get("non_striker")) == false)
                                        T1.add(ind.get("non_striker").toString());
                                    if (T2.contains(ind.get("bowler")) == false)
                                        T2.add(ind.get("bowler").toString());
                                    bal++;

                                } catch (Exception e) {
                                    // TODO: handle exception
                                    System.out.println("ind.get");
                                    over++;
                                    if (over > 20)
                                        break;
                                    bal = 1;
                                    k--;
                                }

                            }
                            JO = (JSONObject) jo.get(1);
                            jo1 = (JSONObject) JO.get("2nd innings");
                            jo2 = (JSONArray) jo1.get("deliveries");
                            for (int k = 0, over = 0, bal = 1; k < jo2.size(); k++) {
                                JSONObject ball = (JSONObject) jo2.get(k);
                                System.out.println(jo2.get(k));
                                if (over > 20)
                                    break;
                                try {
                                    ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    // System.out.print(Integer.toString(over) + "." + Integer.toString(bal));
                                    System.out.println(ind.get("batsman"));
                                    if (T2.contains(ind.get("batsman")) == false)
                                        T2.add(ind.get("batsman").toString());
                                    if (T2.contains(ind.get("non_striker")) == false)
                                        T2.add(ind.get("non_striker").toString());
                                    if (T1.contains(ind.get("bowler")) == false)
                                        T1.add(ind.get("bowler").toString());
                                    bal++;

                                } catch (Exception e) {
                                    // TODO: handle exception
                                    System.out.println("ind.get");
                                    over++;
                                    if (over > 20)
                                        break;
                                    bal = 1;
                                    k--;
                                }

                            }
                            process(T1,T2);

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

    void process(ArrayList<String> T1, ArrayList<String> T2) {
        Log.println(Log.WARN,"process","Started");
        ArrayList<team> t1 =Team1.t1;
        ArrayList<team> t2 =Team2.t2;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcview);
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.rcview1);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://raw.githubusercontent.com/mukesh2k/Backend-JSON/master/data.json",
                response -> {
                    Object obj = null;
                    try {
                        Log.println(Log.WARN,"process","Suceess process");
                        obj = new JSONParser().parse(response);
                    int n1 = T1.size(), n2 = T2.size();
                        ArrayList<Integer> t22 = new ArrayList <Integer>( Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
                        ArrayList<Integer> t23 = new ArrayList <Integer>( Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONArray jo2 = (JSONArray) jsonObject.get("players");
                        Log.println(Log.WARN,"process","Loading");
                    for (int i = 0, f1 = 0, f2 = 0; i < jo2.size(); i++) {
                        JSONObject jo = (JSONObject) jo2.get(i);
                        if (T1.contains(jo.get("Players"))) {
                            team t = new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()),false);
                            t1.add(t);
                        } else if (T2.contains(jo.get("Players"))) {
                            team t = new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()),false);

                            t2.add(t);
                        } else {
                            if (n1 < 11) {
                                team t = new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()),false);

                                t1.add(t);
                                n1++;
                            } else if (n2 < 11) {
                                team t =  new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()),false);
                                t2.add(t);
                                n2++;
                            } else if (t1.size() == 11 && t2.size() == 11)
                                break;
                            new RecyclerViewPlayers().setConfig( recyclerView, getApplicationContext() ,t1, t22);
                            new RecyclerViewPlayers2().setConfig( recyclerView1,getApplicationContext() ,t2 ,t23);
                        }
                    }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // typecasting obj to JSONObject
                    Log.println(Log.WARN,"process", String.valueOf(t1.get(0).point));
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        return;
                    }
                });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

        System.out.println(t1.size()+t2.size());
    }

}
//    obj = new JSONParser().parse(response);
//    int n1 = T1.size(), n2 = T2.size();
//    ArrayList<team> t1 = new ArrayList<>();
//    ArrayList<team> t2 = new ArrayList<>();
//    JSONObject jsonObject = (JSONObject) obj;
//    JSONArray jo2 = (JSONArray) jsonObject.get("players");
//                                for (int i = 0, f1 = 0, f2 = 0; i < jo2.size(); i++) {
//        JSONObject jo = (JSONObject) jo2.get(i);
//        if (T1.contains(jo.get("Players"))) {
//            team t = new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()));
//            t1.add(t);
//        } else if (T2.contains(jo.get("Players"))) {
//            team t = new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()));
//
//            t2.add(t);
//        } else {
//            if (n1 < 12) {
//                team t = new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()));
//
//                t1.add(t);
//                n1++;
//            } else if (n2 < 12) {
//                team t =  new team(jo.get("Players").toString(), "none",Float.parseFloat (jo.get("Credit Value").toString()));
//                t2.add(t);
//                n2++;
//            } else if (t1.size() == 11 && t2.size() == 11)
//                break;
//        }
//    }
