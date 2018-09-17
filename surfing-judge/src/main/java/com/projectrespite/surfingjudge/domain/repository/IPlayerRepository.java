package com.projectrespite.surfingjudge.domain.repository;

import com.projectrespite.surfingjudge.domain.model.response.PlayerResponse;

import java.util.List;

public interface IPlayerRepository {

    List<PlayerResponse> getPlayers();
}
