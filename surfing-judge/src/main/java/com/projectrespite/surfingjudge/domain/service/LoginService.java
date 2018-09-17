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

        var response = new LoginResponse();

        switch (request.getPassword()) {
            case "player":
                response.setStatus("success");
                response.setRole("player");
                break;
            case "judge":
                response.setStatus("success");
                response.setRole("judge");
                break;
            default:
                throw new HttpClientErrorException(HttpStatus.CONFLICT, "password wrong");
        }

        return response;
    }
}
