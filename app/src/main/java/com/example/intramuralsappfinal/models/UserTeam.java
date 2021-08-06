package com.example.intramuralsappfinal.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class UserTeam implements Serializable {
    private String name;
    private String role;
    private String sportType;
    private String teamType;
    private String division;
    private String teamId;
    private ArrayList<Event> schedule;

    public UserTeam(String name, String role, String sportType, String teamType, String division,String teamId, ArrayList<Event> schedule) {
        this.name = name;
        this.role = role;
        this.sportType = sportType;
        this.teamType = teamType;
        this.division = division;
        this.schedule = schedule;
        this.teamId = teamId;
    }

    public UserTeam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setSchedule(ArrayList<Event> schedule) {
        this.schedule = schedule;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public ArrayList<Event> getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Role: " + role + '\n' +
                "Sport: " + sportType + '\n' +
                "Type: " + teamType + '\n' +
                "Division: " + division + '\n';
    }
}
