package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.response.PlayerResponse;
import com.projectrespite.surfingjudge.infrastructure.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerRepository repository;

    @GetMapping(value = "/players")
    public List<PlayerResponse> getPlayers() {

        return repository.getPlayers();
    }
}
