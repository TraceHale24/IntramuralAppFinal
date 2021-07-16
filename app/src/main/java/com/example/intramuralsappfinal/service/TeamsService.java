package com.example.intramuralsappfinal.service;



import com.example.intramuralsappfinal.ServerFacade;
import com.example.intramuralsappfinal.models.User;
import com.example.intramuralsappfinal.models.request.TeamsRequest;
import com.example.intramuralsappfinal.models.response.TeamsResponse;

import java.io.IOException;

public class TeamsService {
    static final String URL_PATH = "/getTeams";
    private User user;

    public TeamsResponse getTeams(TeamsRequest request) throws IOException {
        TeamsResponse response = getServerFacade().getTeams(request, URL_PATH);
        return response;
    }

    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }

}