package com.example.intramuralsappfinal.presenter;



import com.example.intramuralsappfinal.models.request.RegisterRequest;
import com.example.intramuralsappfinal.models.response.RegisterResponse;
import com.example.intramuralsappfinal.service.RegisterService;

import java.io.IOException;

public class RegisterPresenter {
    private final View view;

    public interface View {
    }

    public RegisterPresenter(View view) {
        this.view = view;
    }


    public RegisterResponse register(RegisterRequest registerRequest) throws IOException {
        RegisterService registerServiceProxy = new RegisterService();
        return registerServiceProxy.register(registerRequest);
    }

    RegisterService getRegisterService() {
        return new RegisterService();
    }
}
