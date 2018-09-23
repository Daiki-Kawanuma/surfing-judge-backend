package com.projectrespite.surfingjudge.util;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.response.JudgeResponse;
import lombok.val;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

        val scores = Arrays.asList(-99.0, -99.0, -99.0, -99.0, -99.0);
        judgeEntities.forEach(j -> {
            scores.set(j.getJudgeNumber() - 1, j.getScore());
        });
        response.setScores(scores);

        return response;
    }
}
