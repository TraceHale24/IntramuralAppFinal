package com.example.intramuralsappfinal.models.response;


import com.example.intramuralsappfinal.models.Team;

import java.util.ArrayList;

public class TeamsResponse extends Response{
    private ArrayList<Team> teams;

    public TeamsResponse(String message) {
        super(false,message);
    }


    private TeamsResponse(ArrayList<Team> teams) {
        super(true, null);
        this.teams = teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Team> getTeams() {
        return this.teams;
    }
}
