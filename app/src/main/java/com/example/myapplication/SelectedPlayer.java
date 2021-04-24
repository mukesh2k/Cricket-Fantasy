package com.example.myapplication;

public class SelectedPlayer {
    String name,team,role,value,scored;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getScored() {
        return scored;
    }

    public void setScored(String scored) {
        this.scored = scored;
    }

    public SelectedPlayer(String name, String team, String role, String value, String scored) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.value = value;
        this.scored = scored;
    }
}
