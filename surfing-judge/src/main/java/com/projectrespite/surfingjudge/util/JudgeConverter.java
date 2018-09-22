package com.projectrespite.surfingjudge.util;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.response.JudgeResponse;
import lombok.val;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JudgeConverter implements Function<List<JudgeEntity>, JudgeResponse> {

    @Override
    public JudgeResponse apply(List<JudgeEntity> judgeEntities) {

        val first = judgeEntities.get(0);
        val response = new JudgeResponse();
        response.setPlayerNumber(first.getPlayerNumber());
        response.setPlayerName(first.getPlayerName());
        response.setPlayerColor(first.getPlayerColor());
        response.setWave(first.getWave());

        val scores = judgeEntities.stream()
                .map(JudgeEntity::getScore)
                .collect(Collectors.toList());

        response.setScores(scores);

        return response;
    }
}
