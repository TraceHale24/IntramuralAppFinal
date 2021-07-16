package com.example.intramuralsappfinal.service;



import com.example.intramuralsappfinal.ServerFacade;
import com.example.intramuralsappfinal.models.User;
import com.example.intramuralsappfinal.models.request.LoginRequest;
import com.example.intramuralsappfinal.models.response.LoginResponse;

import java.io.IOException;

public class LoginService {

    static final String URL_PATH = "/login";
    private User user;

    public LoginResponse login(LoginRequest request) throws IOException {
        LoginResponse response = getServerFacade().login(request, URL_PATH);
        return response;
    }

    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
