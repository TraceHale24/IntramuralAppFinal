package com.example.intramuralsappfinal.models;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Team {
    private String teamId;
    private String name;
    private String division;
    private String teamType;
    private ArrayList<Event> schedule;
    private String capacity;
    private ArrayList<TeamMember> members ;
    private String sportType;
    private String captain;
    private String captainId;
    private boolean isOpen;
    public Team() {
    }

    public Team(String name, String division, String  teamType, ArrayList<Event> schedule,
                String  capacity, ArrayList<TeamMember> members , String sportType, String captain, String captainId, boolean isOpen) {
        this.teamId = UUID.randomUUID().toString();
        this.name = name;
        this.division = division;
        this.teamType = teamType;
        this.schedule = schedule;
        this.capacity = capacity;
        this.members  = members ;
        this.sportType = sportType;
        this.captain = captain;
        this.captainId = captainId;
        this.isOpen = isOpen;
    }

    public Team(String teamId, String name, String division, String teamType, ArrayList<Event> schedule, String capacity, ArrayList<TeamMember> members, String sportType, String captain, String captainId, boolean isOpen) {
        this.teamId = teamId;
        this.name = name;
        this.division = division;
        this.teamType = teamType;
        this.schedule = schedule;
        this.capacity = capacity;
        this.members = members;
        this.sportType = sportType;
        this.captain = captain;
        this.captainId = captainId;
        this.isOpen = isOpen;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getCaptainId() {
        return captainId;
    }

    public void setCaptainId(String captainId) {
        this.captainId = captainId;
    }

    public void setTeamId(String  teamId) {
        this.teamId = teamId;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String  getTeamId() {
        return this.teamId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getDivision() {
        return this.division;
    }

    public void setDivision(String  division) { this.division = division; }

    public String getTeamType() {
        return this.teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public ArrayList<Event> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(ArrayList<Event> schedule) {
        this.schedule = schedule;
    }

    public void addEvent(Event event) {
        this.schedule.add(event);
    }

    public String  getCapacity() {
        return this.capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public ArrayList<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<TeamMember> members) {
        this.members = members;
    }

    public void addPlayer(TeamMember player) {
        this.members.add(player);
    }

    public void removePlayer(TeamMember player) {
        this.members .remove(player);
    }

    @Override
    public String toString() {
        return

                "Team Name: " + name + '\n' +
                "Division: " + division + '\n' +
                "Type: " + teamType + '\n' +
                "Team Capacity: " + capacity + '\n' +
                "Sport: " + sportType + '\n' +
                "Captain: " + captain + '\n' +
                "isOpen: " + isOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Team)) {
            return false;
        }
        Team team = (Team) o;
        return teamId.equals(team.teamId)
                || (name.equals(team.name) && division == team.division && teamType == team.teamType);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, teamId, division, teamType, schedule, capacity, members );
    }

}