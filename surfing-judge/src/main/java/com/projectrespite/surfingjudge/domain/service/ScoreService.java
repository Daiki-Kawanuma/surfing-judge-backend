package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.repository.ICompetitionRepository;
import com.projectrespite.surfingjudge.domain.repository.IJudgeRepository;
import com.projectrespite.surfingjudge.util.ScoreConverter;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {

    private IJudgeRepository judgeRepository;
    private ICompetitionRepository competitionRepository;

    public List<ScoreResponse> getScores(int round, int heat) {

        val judges = judgeRepository.findJudgeByRoundHeat(round, heat);
        val competitions = competitionRepository.getCompetitionByRoundHeat(round,heat);

        val scores = competitions.stream()
                .map(new ScoreConverter(judges))
                .sorted(Comparator.comparing(ScoreResponse::getAggregate).reversed())
                .collect(Collectors.toList());

        int i[] = {1};
        scores.forEach(s -> {
            s.setRank(i[0]);
            i[0]++;
        });

        return scores.stream()
                .sorted(Comparator.comparing(ScoreResponse::getColorOrder))
                .collect(Collectors.toList());
    }
}
