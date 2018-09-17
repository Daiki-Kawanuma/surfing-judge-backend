package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.response.PlayerResponse;
import com.projectrespite.surfingjudge.domain.repository.IPlayerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "プレイヤー", description = "プレイヤーを取得する")
@AllArgsConstructor
public class PlayerController {

    private IPlayerRepository repository;

    @GetMapping(value = "/players")
    @ApiOperation(value = "プレイヤーを取得する", response = PlayerResponse.class, responseContainer = "List")
    public List<PlayerResponse> getPlayers() {

        return repository.getPlayers();
    }
}
