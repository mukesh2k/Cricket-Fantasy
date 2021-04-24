package com.example.myapplication;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MatchArrayDev {



    File fi = new File("/JSON Files");
    ArrayList<MatchArray> matches;
    String[] pat = new String[]; // For each pathname in the pathnames array
    pat= fi.list();

      for(int i=0;i<pat.length ; i++)
    {

        // Print the names of files and directories
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(pat[i]));
        } catch (IOException e) {
            e.printStackTrace();
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
        i = 0;
        while (iterator.hasNext()) {
            s[i++] = (String) iterator.next();
        }
        JSONArray te = (JSONArray) jo.get("teams");
        iterator = te.iterator();// teams
        while (iterator.hasNext()) {
            s[i++] = (String) iterator.next();
        }
        MatchArray m = new MatchArray(pat[i], city, comp, s[1], s[2], s[0].split("T")[0], s[0].split("T")[1].substring(0, 5));
        matches.add(m);
    }
}
