package com.example.intramuralsappfinal.models.response;


import com.example.intramuralsappfinal.models.User;

public class LoginResponse extends Response {
    private User user;

    public LoginResponse(String message) {
        super(false,message);
    }

    public LoginResponse(User user) {
        super(true, null);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
