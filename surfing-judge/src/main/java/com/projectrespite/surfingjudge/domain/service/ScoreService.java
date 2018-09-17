package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.Judge;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.repository.IJudgeRepository;
import com.projectrespite.surfingjudge.util.ScoreConverter;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {

    private IJudgeRepository repository;

    public List<ScoreResponse> getScores(int round, int heat) {

        val judges = repository.findJudgeByRoundHeat(round, heat);

        Map<Integer, List<Judge>> groupByPlayer = judges.stream()
                .collect(Collectors.groupingBy(Judge::getPlayerNumber));

        return groupByPlayer.entrySet().stream()
                .map(Map.Entry::getValue)
                .map(new ScoreConverter())
                .collect(Collectors.toList());
    }
}
