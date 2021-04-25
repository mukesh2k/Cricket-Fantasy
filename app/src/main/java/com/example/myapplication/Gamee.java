package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Random;

public class Gamee extends AppCompatActivity {
    public static Context con;
     double total=0;
    public static ArrayList<SelectedPlayer> S=new ArrayList<>();
    public static ArrayList<Integer>kzy=new ArrayList<>();
    ViewPager viewPager;
    TextView team;
    TextView ini;
    TextView poin;
    TextView over;
    boolean t=false;
    String inni,over1;
    MyAdapter1 adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamee);
        TabLayout tabs = findViewById(R.id.tab1);
        tabs.addTab(tabs.newTab().setText("Team 1"));
        tabs.addTab(tabs.newTab().setText("Team 2"));
        con=getApplicationContext();
        load();
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPager viewPager = findViewById(R.id.view_pager1);
        tabs.setupWithViewPager(viewPager);
        adapter = new MyAdapter1(this, getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, TeamSelection.ui,
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

                            JSONObject jo1 = (JSONObject) JO.get("1st innings");
                            inni="1st innings";
                            JSONArray jo2 = (JSONArray) jo1.get("deliveries");
                            for (int k = 0, over = 0, bal = 1; k < jo2.size(); k++) {
                                JSONObject ball = (JSONObject) jo2.get(k);
                                System.out.println(jo2.get(k));
                                if (over > 20)
                                    break;
                                String ove=(Integer.toString(over) + "." + Integer.toString(bal));
                                try {
                                    System.out.println(Integer.toString(over) + "." + Integer.toString(bal));
                                    ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    ind.get("batsman");
                                       ove=(Integer.toString(over) + "." + Integer.toString(bal));

                                    }
                                    catch (Exception e) {
                                    //break;
                                        //k++;
                                        System.out.println("ind.get");
                                        over++;
                                        if (over > 20)
                                            break;
                                        bal = 1;
                                        k--;
                                        continue;

                                    }
                                    over1=ove;
                                try {
                                    System.out.println(1);
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    JSONObject wick = (JSONObject) ind.get("wicket");
                                    int cost=0;
                                    System.out.println(wick.toJSONString());
                                    System.out.println(wick.get("kind").toString());
                                    switch (wick.get("kind").toString())
                                    {
                                        case "caught":
                                            cost=40;
                                            break;
                                        case "bowled":
                                            cost=33;
                                            break;
                                        case "run out":
                                            cost=25;
                                            break;
                                        case "retired hurt":
                                            return;
                                        case "stumped":
                                            cost=25;
                                            break;
                                        case "caught and bowled":
                                            cost=40;
                                            break;
                                        case "hit wicket":
                                            cost=25;
                                            break;
                                        default:
                                            break;
                                    }
                                    System.out.println(wick.get("kind").toString());
                                    JSONObject bow = (JSONObject) ind.get("bowler");
                                    set1(cost,wick.get("kind").toString(),ove,bow.toString());
                                    try {
                                        JSONArray fd = (JSONArray) jo1.get("fielders");
                                        for (Object object : fd) {
                                            set1(cost,wick.get("kind").toString(),ove,object.toString());
                                        }
                                        System.out.println(2);

                                    }catch (Exception e)
                                    {
                                        System.out.println(3);

                                    }

                                    bal++;


                                } catch (Exception e) {
                                    // TODO: handle exception
                                    //batmans
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    String s=ind.get("batsman").toString();
                                    JSONObject po= (JSONObject) ind.get("runs");
                                    String run=  po.get("batsman").toString();
                                    pro(run,s,ove);
                                    System.out.println(4);
                                    bal++;

                                }



                            }
                            JO = (JSONObject) jo.get(1);
                            inni="2nd innings";
                            jo1 = (JSONObject) JO.get("2nd innings");
                            jo2 = (JSONArray) jo1.get("deliveries");
                            for (int k = 0, over = 0, bal = 1; k < jo2.size(); k++) {
                                JSONObject ball = (JSONObject) jo2.get(k);
                                System.out.println(jo2.get(k));
                                if (over > 20)
                                    break;
                                String ove=(Integer.toString(over) + "." + Integer.toString(bal));
                                try {
                                    System.out.println(Integer.toString(over) + "." + Integer.toString(bal));
                                    ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    ind.get("batsman");
                                    ove=(Integer.toString(over) + "." + Integer.toString(bal));

                                }
                                catch (Exception e) {
                                    //break;
                                    //k++;
                                    System.out.println("ind.get");
                                    over++;
                                    if (over > 20)
                                        break;
                                    bal = 1;
                                    k--;
                                    continue;

                                }

                                try {
                                    System.out.println(1);
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    JSONObject wick = (JSONObject) ind.get("wicket");
                                    int cost=0;
                                    switch (wick.get("kind").toString())
                                    {
                                        case "caught":
                                            cost=40;
                                            break;
                                        case "bowled":
                                            cost=33;
                                            break;
                                        case "run out":
                                            cost=25;
                                            break;
                                        case "retired hurt":
                                            return;
                                        case "stumped":
                                            cost=25;
                                            break;
                                        case "caught and bowled":
                                            cost=40;
                                            break;
                                        case "hit wicket":
                                            cost=25;
                                            break;
                                        default:
                                            break;
                                    }
                                    JSONObject bow = (JSONObject) ind.get("bowler");
                                    set1(cost,wick.get("kind").toString(),ove,bow.toString());
                                    try {
                                        JSONArray fd = (JSONArray) jo1.get("fielders");
                                        for (Object object : fd) {
                                            set1(cost,wick.get("kind").toString(),ove,object.toString());
                                        }
                                        System.out.println(2);

                                    }catch (Exception e)
                                    {
                                        System.out.println(3);

                                    }

                                    bal++;

                                } catch (Exception e) {
                                    // TODO: handle exception
                                    //batmans
                                    JSONObject ind = (JSONObject) ball.get((Integer.toString(over) + "." + Integer.toString(bal)));
                                    String s=ind.get("batsman").toString();
                                    JSONObject po= (JSONObject) ind.get("runs");
                                    String run=  po.get("batsman").toString();
                                    pro(run,s,ove);
                                    System.out.println(4);
                                    bal++;

                                }

                            }


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
        Button pm=findViewById(R.id.balact);
        pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<BallScene.S.size();i++)
                {
                    System.out.println( BallScene.S.get(i).getOver());
                }
                upda(tabs);

            }
        });



    }
     public void upda(TabLayout tabs)
    {
        team=findViewById(R.id.tm);
        ini=findViewById(R.id.batt);
        poin=findViewById(R.id.live);
        over=findViewById(R.id.ov);
        over.setText("Overs:"+over1);
        team.setText(Team1.tm+" VS "+Team2.tm);
        poin.setText(String.valueOf(total)+"pts");
        ini.setText(inni);
        if(t==true ){poin.setTextColor(Color.GREEN);
        t=false;
    }
        else{
        poin.setTextColor(Color.RED);
        t=true;
    }
//        adapter = new MyAdapter1(getApplicationContext(), getSupportFragmentManager(), tabs.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }
    void load()
    {
        int i;
        for(i=0;i<Team1.t1.size();i++)
        {
            if(!Team1.t1.get(i).getTit().equals("none"))
            {

                SelectedPlayer po=new SelectedPlayer(Team1.t1.get(i).getName(),Team1.tm,Team1.t1.get(i).getTit(),Team1.t1.get(i).getPoint().toString(),"0.0");
                S.add(po);
            }
            if(!Team2.t2.get(i).getTit().equals("none"))
            {
                SelectedPlayer po=new SelectedPlayer(Team2.t2.get(i).getName(),Team2.tm,Team2.t2.get(i).getTit(),Team2.t2.get(i).getPoint().toString(),"0.0");
                S.add(po);
            }
        }
        for(i=0;i<S.size();i++)kzy.add(i);
    }
    void set1(int val,String r,String over,String comp)
    {
        System.out.println(32);
        for(int po=0;po<S.size();po++)
        {
            System.out.println(po);
            if(S.get(po).getName().equals(comp))
            {
                double cur=0;
                switch(S.get(po).getRole())
                {
                    case "Player":
                        cur=val;
                        break;
                    case "Vice Captain":
                        cur=1.5*val;
                        break;
                    case "Captain":
                        cur=2*val;
                        break;

                }
                S.get(po).setScored(String.valueOf((Double.parseDouble(S.get(po).getScored())+cur)));
                PointInfo P=new PointInfo(r+" wicket",over,S.get(po).getName(),"+"+String.valueOf(cur),S.get(po).getTeam());
                BallScene.key.add(BallScene.u++);
                BallScene.S.add(P);
                total+=cur;
                break;

            }
        }

    }
    void pro(String run,String player,String over)
    {
        for(int po=0;po<S.size();po++)
        {

            if(S.get(po).getName().equals(player))
            {
                double cur=0;
                switch(S.get(po).getRole())
                {
                    case "Player":
                        cur=Integer.parseInt(run);
                        break;
                    case "Vice Captain":
                        cur=1.5*Integer.parseInt(run);
                        break;
                    case "Captain":
                        cur=2*Integer.parseInt(run);
                        break;

                }
                System.out.println(po);
                int u= S.get(po).getRun()+Integer.parseInt(run);
                S.get(po).setRun(u);
                S.get(po).setScored(String.valueOf((Double.parseDouble(S.get(po).getScored())+cur)));
                PointInfo P=new PointInfo("Runs Scored ",over,S.get(po).getName(),"+"+String.valueOf(cur),S.get(po).getTeam());
                BallScene.key.add(BallScene.u++);
                BallScene.S.add(P);
                total+=cur;
                if(u>=50&& S.get(po).getPly()!="fifty"&& S.get(po).getPly()!="hundred")
                {
                    P=new PointInfo("Fifty scored",over,S.get(po).getName(),"+"+String.valueOf(58),S.get(po).getTeam());
                    BallScene.key.add(BallScene.u++);
                    BallScene.S.add(P);
                    S.get(po).setPly("fifty");
                    total+=58;
                }
                if(u>=100&& S.get(po).getPly()!="hund")
                {
                    P=new PointInfo("Hundread scored",over,S.get(po).getName(),"+"+String.valueOf(116),S.get(po).getTeam());
                    BallScene.key.add(BallScene.u++);
                    BallScene.S.add(P);
                    S.get(po).setPly("hundred");
                    total+=116;
                }
                System.out.println(po);
                break;

            }
        }
    }

}

