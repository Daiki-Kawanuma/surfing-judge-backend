package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.request.LoginRequest;
import com.projectrespite.surfingjudge.domain.model.response.LoginResponse;
import com.projectrespite.surfingjudge.domain.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "ログイン", description = "ログインする")
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "ログイン")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return service.login(request);
    }
}
