package com.example.intramuralsappfinal.models;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Team {
    private UUID teamId;
    private String name;
    private Division division;
    private TeamType teamType;
    private ArrayList<Event> schedule;
    private int capacity;
    private ArrayList<User> players;
    private String sport;

    enum Division { Lower, Upper };
    enum TeamType { Male, Female, Coed };

    public Team(String name, Division division, TeamType teamType, ArrayList<Event> schedule,
                int capacity, ArrayList<User> players, String sport) {
        this.teamId = UUID.randomUUID();
        this.name = name;
        this.division = division;
        this.teamType = teamType;
        this.schedule = schedule;
        this.capacity = capacity;
        this.players = players;
        this.sport = sport;
    }


    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public UUID getTeamId() {
        return this.teamId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Division getDivision() {
        return this.division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public TeamType getTeamType() {
        return this.teamType;
    }

    public void setTeamType(TeamType teamType) {
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

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
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