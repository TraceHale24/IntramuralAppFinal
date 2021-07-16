package com.example.intramuralsappfinal.Tasks;



import android.os.AsyncTask;

import com.example.intramuralsappfinal.models.request.TeamsRequest;
import com.example.intramuralsappfinal.models.response.TeamsResponse;
import com.example.intramuralsappfinal.presenter.TeamsPresenter;

import java.io.IOException;

public class TeamsTask extends AsyncTask<TeamsRequest, Void, TeamsResponse> {

    private final TeamsPresenter presenter;
    private final Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void teamsSuccessful(TeamsResponse teamsResponse);
        void teamsUnsuccessful(TeamsResponse teamsResponse);
        void handleException(Exception ex);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter this task should use to login.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public TeamsTask(TeamsPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected TeamsResponse doInBackground(TeamsRequest... teamsRequests) {
        TeamsResponse teamsResponse = null;

        try {
            teamsResponse = presenter.getTeams(teamsRequests[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teamsResponse;
    }

    @Override
    protected void onPostExecute(TeamsResponse teamsResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else if(teamsResponse.isSuccess()) {
            observer.teamsSuccessful(teamsResponse);
        } else {
            observer.teamsUnsuccessful(teamsResponse);
        }
    }
}