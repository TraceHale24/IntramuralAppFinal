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
    private ArrayList<User> players;
    private String sportType;
    private String captain;
    private String captainId;

    public Team() {
    }

    public Team(String name, String division, String  teamType, ArrayList<Event> schedule,
                String  capacity, ArrayList<User> players, String sportType, String captain, String captainId) {
        this.teamId = UUID.randomUUID().toString();
        this.name = name;
        this.division = division;
        this.teamType = teamType;
        this.schedule = schedule;
        this.capacity = capacity;
        this.players = players;
        this.sportType = sportType;
        this.captain = captain;
        this.captainId = captainId;
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

    public ArrayList<User> getPlayers() {
        return this.players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public void addPlayer(User player) {
        this.players.add(player);
    }

    public void removePlayer(User player) {
        this.players.remove(player);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", name='" + name + '\'' +
                ", division='" + division + '\'' +
                ", teamType='" + teamType + '\'' +
                ", schedule=" + schedule +
                ", capacity='" + capacity + '\'' +
                ", players=" + players +
                ", sportType='" + sportType + '\'' +
                ", captain='" + captain + '\'' +
                ", captainId='" + captainId + '\'' +
                '}';
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
        return Objects.hash(name, teamId, division, teamType, schedule, capacity, players);
    }

}