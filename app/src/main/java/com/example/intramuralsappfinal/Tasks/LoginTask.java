package com.example.intramuralsappfinal.Tasks;

import android.os.AsyncTask;

import com.example.intramuralsappfinal.models.request.LoginRequest;
import com.example.intramuralsappfinal.models.response.LoginResponse;
import com.example.intramuralsappfinal.presenter.LoginPresenter;

import java.io.IOException;

public class LoginTask extends AsyncTask<LoginRequest, Void, LoginResponse> {

    private final LoginPresenter presenter;
    private final Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void loginSuccessful(LoginResponse loginResponse);
        void loginUnsuccessful(LoginResponse loginResponse);
        void handleException(Exception ex);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter this task should use to login.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public LoginTask(LoginPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected LoginResponse doInBackground(LoginRequest... loginRequests) {
        LoginResponse loginResponse = null;

        try {
            loginResponse = presenter.login(loginRequests[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    protected void onPostExecute(LoginResponse loginResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else if(loginResponse.isSuccess()) {
            observer.loginSuccessful(loginResponse);
        } else {
            observer.loginUnsuccessful(loginResponse);
        }
    }
}