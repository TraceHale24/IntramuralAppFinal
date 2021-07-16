package com.example.intramuralsappfinal;

import com.example.intramuralsappfinal.models.User;
import com.example.intramuralsappfinal.models.request.LoginRequest;
import com.example.intramuralsappfinal.models.request.RegisterRequest;
import com.example.intramuralsappfinal.models.request.TeamsRequest;
import com.example.intramuralsappfinal.models.response.LoginResponse;
import com.example.intramuralsappfinal.models.response.RegisterResponse;
import com.example.intramuralsappfinal.models.response.TeamsResponse;

import java.io.IOException;

public class ServerFacade {
    User fakeUser = new User("Trace", "thale8", "thale8@byu.edu", "9542926910", true);
    public LoginResponse login(LoginRequest request, String urlPath) throws IOException {
        LoginResponse response = new LoginResponse(fakeUser);

        return response;
    }

    public RegisterResponse register(RegisterRequest request, String urlPath) throws IOException {
        RegisterResponse response = new RegisterResponse(fakeUser);
        return response;
    }

    public TeamsResponse getTeams(TeamsRequest request, String urlPath) throws IOException {
        TeamsResponse response = new TeamsResponse(fakeUser.getNetId());
        return response;
    }
}
