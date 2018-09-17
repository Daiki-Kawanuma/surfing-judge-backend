package com.projectrespite.surfingjudge.util;

import com.projectrespite.surfingjudge.domain.model.data.Judge;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import lombok.val;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoreConverter implements Function<List<Judge>, ScoreResponse> {

    @Override
    public ScoreResponse apply(List<Judge> judges) {

        val first = judges.get(0);
        val response = new ScoreResponse();
        response.setName(first.getName());
        response.setPlayerNumber(first.getPlayerNumber());

        List<Double> scores = judges.stream()
                .collect(Collectors.groupingBy(Judge::getWave))
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
