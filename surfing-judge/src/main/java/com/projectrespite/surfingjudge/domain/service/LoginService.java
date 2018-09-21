package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.request.LoginRequest;
import com.projectrespite.surfingjudge.domain.model.response.LoginResponse;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class LoginService {

    public LoginResponse login(LoginRequest request) {

        LoginResponse response;

        switch (request.getPassword()) {
            case "player":
                response = new LoginResponse("success", "player");
                break;
            case "judge":
                response = new LoginResponse("success", "judge");
                break;
            default:
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "password wrong");
        }

        return response;
    }
}
