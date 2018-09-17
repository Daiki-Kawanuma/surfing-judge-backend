package com.projectrespite.surfingjudge.application.controller;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity exceptionHandler(HttpStatusCodeException e) {

        val map = new HashMap<String, String>();
        map.put("status", "failure");
        map.put("error", e.getStatusText());

        return ResponseEntity.status(e.getStatusCode()).body(map);
    }
}
