package com.example.intramuralsappfinal.models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class User implements Comparable<User>, Serializable {
    private String name;
    private String netId;
    private String email;
    private String phone;
    private boolean isMale;

    public User() { }

    public User(String name, String netId, String email, String phone, boolean isMale) {
        this.name = name;
        this.netId = netId;
        this.email = email;
        this.phone = phone;
        this.isMale = isMale;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetId() {
        return this.netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIsMale() {
        return this.isMale;
    }

    public boolean getIsMale() {
        return this.isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
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
        return netId.equals(user.netId);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, netId, email, phone, isMale);
    }

    @Override
    public int compareTo(User o) {
        return this.getNetId().compareTo(o.getNetId());
    }
}