package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.response.PlayerResponse;
import com.projectrespite.surfingjudge.domain.repository.IPlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlayerController {

    private IPlayerRepository repository;

    @GetMapping(value = "/players")
    public List<PlayerResponse> getPlayers() {

        return repository.getPlayers();
    }
}
