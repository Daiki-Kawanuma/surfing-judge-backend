package com.projectrespite.surfingjudge.util;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ScoreConverter implements Function<CompetitionEntity, ScoreResponse> {

    private List<JudgeEntity> judges;

    @Override
    public ScoreResponse apply(CompetitionEntity entity) {

        val response = new ScoreResponse();
        response.setPlayerNumber(entity.getPlayerNumber());
        response.setPlayerName(entity.getPlayerName());
        response.setPlayerColor(entity.getPlayerColor());

        List<Double> scores = judges.stream()
                .filter(j -> j.getPlayerNumber() == entity.getPlayerNumber())
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
