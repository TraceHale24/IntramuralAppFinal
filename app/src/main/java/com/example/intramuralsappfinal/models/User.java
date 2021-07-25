package com.example.intramuralsappfinal.models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private String netid;
    private String email;
    private String phoneNumber;
    private String gender;
    private String school;
    private HashMap<String, UserTeam> teams;

    public User() { }

    public User(String name, String netId, String email, String phone, String gender, String school, HashMap<String, UserTeam> UserTeams) {
        this.name = name;
        this.netid = netId;
        this.email = email;
        this.phoneNumber = phone;
        this.gender = gender;
        this.school = school;
        this.teams = UserTeams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetid() {
        return netid;
    }

    public void setNetid(String netid) {
        this.netid = netid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public HashMap<String, UserTeam> getTeams() {
        return teams;
    }

    public void setTeams(HashMap<String, UserTeam> UserTeams) {
        this.teams = UserTeams;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return netid.equals(user.netid);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, netid, email, phoneNumber, gender, school);
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", netid='" + netid + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", UserTeams=" + teams +
                '}';
    }
}