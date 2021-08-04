package com.example.intramuralsappfinal.models;

public class TeamMember {
    private String role;
    private String name;
    private String phone;
    private String userId;

    public TeamMember(String role, String name, String phone, String userId) {
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.userId = userId;
    }

    public TeamMember() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "TeamMember{" +
                "role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
