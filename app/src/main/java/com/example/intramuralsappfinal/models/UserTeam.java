package com.example.intramuralsappfinal.models;

public class UserTeam {
    private String name;
    private String role;
    private String sportType;
    private String teamType;

    public UserTeam() {
    }

    public UserTeam(String name, String role, String sportType, String teamType) {
        this.name = name;
        this.role = role;
        this.sportType = sportType;
        this.teamType = teamType;
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


    @Override
    public String toString() {
        return "UserTeam{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", sportType='" + sportType + '\'' +
                ", teamType='" + teamType + '\'' +
                '}';
    }
}