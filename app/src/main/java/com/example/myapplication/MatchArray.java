package com.example.myapplication;

public class MatchArray {
    String id;
    String venue;
    String type;

    public MatchArray(String id, String venue, String type, String team1, String team2, String date, String time) {
        this.id = id;
        this.venue = venue;
        this.type = type;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getVenue() {
        return venue;
    }

    public String getType() {
        return type;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    String team1;
    String team2;
    String date;
    String time;


}
