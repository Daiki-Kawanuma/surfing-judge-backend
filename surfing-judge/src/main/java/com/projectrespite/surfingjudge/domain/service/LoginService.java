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

        if(request.getPassword() == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "password is not contained");

        switch (request.getPassword()) {
            case "player":
                return new LoginResponse("success", "player");
            case "judge":
                return new LoginResponse("success", "judge");
            default:
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "password wrong");
        }
    }
}
