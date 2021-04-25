package com.example.myapplication;

public class PointInfo {
    String info;
    String over;
    String player;
    String gain;

    public void setInfo(String info) {
        this.info = info;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public PointInfo(String info, String over, String player, String gain, String team) {
        this.info = info;
        this.over = over;
        this.player = player;
        this.gain = gain;
        this.team = team;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    String team;

    public String getInfo() {
        return info;
    }

    public String getOver() {
        return over;
    }

    public String getPlayer() {
        return player;
    }

    public String getGain() {
        return gain;
    }
}
