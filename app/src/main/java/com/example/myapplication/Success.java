package com.example.myapplication;

public class Success {
    String key,data,time,team1,team2,point;

    public String getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public String getTime() {
        return time;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getPoint() {
        return point;
    }

    public Success(String key, String data, String time, String team1, String team2, String point) {
        this.key = key;
        this.data = data;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
        this.point = point;
    }
}
