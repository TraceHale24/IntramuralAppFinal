package com.example.intramuralsappfinal.models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private String netId;
    private String email;
    private String phone;
    private String gender;
    private String school;
    private ArrayList<UserTeam> teams;

    public User() { }

    public User(String name, String netId, String email, String phone, String gender, String school, ArrayList<UserTeam> teams) {
        this.name = name;
        this.netId = netId;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.school = school;
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public ArrayList<UserTeam> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<UserTeam> teams) {
        this.teams = teams;
    }
}