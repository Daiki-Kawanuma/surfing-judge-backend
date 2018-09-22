package com.projectrespite.surfingjudge.util;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import lombok.val;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoreConverter implements Function<List<JudgeEntity>, ScoreResponse> {

    @Override
    public ScoreResponse apply(List<JudgeEntity> judgeEntities) {

        val first = judgeEntities.get(0);
        val response = new ScoreResponse();
        response.setPlayerName(first.getPlayerName());
        response.setPlayerNumber(first.getPlayerNumber());
        response.setPlayerColor(first.getPlayerColor());

        List<Double> scores = judgeEntities.stream()
                .collect(Collectors.groupingBy(JudgeEntity::getWave))
                .entrySet()
                .stream()
                .map(s -> s.getValue().stream().map(e -> e.getScore()).collect(Collectors.toList()))
                .map(s -> MathUtil.average(s))
                .collect(Collectors.toList());
        response.setScores(scores);
        response.setAggregate(MathUtil.sumBestAndSecondBest(scores));
        return response;
    }
}
