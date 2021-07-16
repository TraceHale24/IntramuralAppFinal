package com.example.intramuralsappfinal.models.response;


import com.example.intramuralsappfinal.models.User;

public class RegisterResponse extends Response{
    private User user;

    public RegisterResponse(String message) {
        super(false,message);
    }

    public RegisterResponse(User user) {
        super(true, null);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
