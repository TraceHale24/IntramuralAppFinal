package com.example.intramuralsappfinal.presenter;


import com.example.intramuralsappfinal.models.request.TeamsRequest;
import com.example.intramuralsappfinal.models.response.TeamsResponse;
import com.example.intramuralsappfinal.service.TeamsService;

import java.io.IOException;

public class TeamsPresenter {
    private final View view;

    public interface View {
    }

    public TeamsPresenter(View view) {
        this.view = view;
    }


    public TeamsResponse getTeams(TeamsRequest request) throws IOException {
        TeamsService teamsServiceProxy = new TeamsService();
        return teamsServiceProxy.getTeams(request);
    }

    TeamsService getTeamsService() {
        return new TeamsService();
    }
}
