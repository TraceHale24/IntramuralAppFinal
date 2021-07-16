package com.example.intramuralsappfinal.presenter;




import com.example.intramuralsappfinal.models.request.LoginRequest;
import com.example.intramuralsappfinal.models.response.LoginResponse;
import com.example.intramuralsappfinal.service.LoginService;

import java.io.IOException;

public class LoginPresenter {
    private final View view;

    public interface View {
    }

    public LoginPresenter(View view) {
        this.view = view;
    }


    public LoginResponse login(LoginRequest loginRequest) throws IOException {
        LoginService loginServiceProxy = new LoginService();
        return loginServiceProxy.login(loginRequest);
    }

    LoginService getLoginService() {
        return new LoginService();
    }
}
