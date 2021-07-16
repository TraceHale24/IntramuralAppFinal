package com.example.intramuralsappfinal.models.request;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String school;
    private boolean isMale;

    private RegisterRequest() {
    this.username = null;
    this.password = null;
    this.email = null;
    this.phoneNumber = null;
    this.isMale = true;
    this.school = school;
    }

    public RegisterRequest(String username, String password, String email, String phoneNumber, boolean isMale, String school){
        this.username = username;
        this.password = password;
        this. email = email;
        this.phoneNumber = phoneNumber;
        this.isMale = isMale;
        this.school = school;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
