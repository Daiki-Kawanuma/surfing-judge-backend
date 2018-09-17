package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.Judge;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.repository.IJudgeRepository;
import com.projectrespite.surfingjudge.util.AggregateUtil;
import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {

    private IJudgeRepository repository;

    public List<ScoreResponse> getScores(int round, int heat) {

        val judges = repository.findJudgeByRoundHeat(round, heat);
        var scores = new ArrayList<ScoreResponse>();

        Map<Integer, List<Judge>> groupbyPlayer = judges.stream().collect(Collectors.groupingBy(Judge::getPlayerNumber));

        return groupbyPlayer.entrySet().stream()
                .map(e -> e.getValue())
                .map(converter)
                .collect(Collectors.toList());
    }

    private Function<List<Judge>, ScoreResponse> converter = (j) -> {
        val first = j.get(0);
        val response = new ScoreResponse();
        response.setName(first.getName());
        response.setPlayerNumber(first.getPlayerNumber());

        List<Double> scores = j.stream()
                .collect(Collectors.groupingBy(Judge::getWave))
                .entrySet()
                .stream()
                .map(s -> s.getValue().stream().map(e -> e.getScore()).collect(Collectors.toList()))
                .map(s -> AggregateUtil.average(s))
                .collect(Collectors.toList());
        response.setScores(scores);
        response.setAggregate(AggregateUtil.average(scores));
        return response;
    };
}
