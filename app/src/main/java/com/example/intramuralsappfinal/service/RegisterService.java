package com.example.intramuralsappfinal.service;


import com.example.intramuralsappfinal.ServerFacade;
import com.example.intramuralsappfinal.models.User;
import com.example.intramuralsappfinal.models.request.RegisterRequest;
import com.example.intramuralsappfinal.models.response.RegisterResponse;

import java.io.IOException;

public class RegisterService {

    static final String URL_PATH = "/register";
    private User user;

    public RegisterResponse register(RegisterRequest request) throws IOException {
        RegisterResponse response = getServerFacade().register(request, URL_PATH);
        return response;
    }

    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
