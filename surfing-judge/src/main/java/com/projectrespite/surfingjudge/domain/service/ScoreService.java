package com.projectrespite.surfingjudge.domain.service;

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

@Service
@AllArgsConstructor
public class ScoreService {

    private IJudgeRepository repository;

    public List<ScoreResponse> getScores(int round, int heat) {

        val judges = repository.findJudgeByRoundHeat(round, heat);
        var scores = new ArrayList<ScoreResponse>();

        judges.forEach(judge -> {

            // 既に存在している挿入済みのスコアか確認
            val optional = scores.stream()
                    .filter(s -> s.getPlayerNumber() == judge.getPlayerNumber())
                    .findFirst();

            if (optional.isPresent()) {

                // とあるジャッジの点数を追加
                val score = optional.get();
                score.getJudgedScores()[judge.getWave() - 1][judge.getJudgeNumber() - 1]
                        = judge.getScore();

            } else {

                // 新たにスコアを生成
                val score = new ScoreResponse();
                score.setPlayerNumber(judge.getPlayerNumber());
                score.setName(judge.getName());
                score.setScores(new ArrayList<>());
                score.getJudgedScores()[judge.getWave() - 1][judge.getJudgeNumber() - 1]
                        = judge.getScore();

                scores.add(score);
            }
        });

        // 集計値を追加
        scores.forEach(score -> {

            Arrays.stream(score.getJudgedScores()).forEach(array -> {
                if (Arrays.stream(array).sum() > 0) {
                    score.getScores().add(AggregateUtil.average(array));
                }
            });

            score.setAggregate(AggregateUtil.sumBestAndSecondBest(score.getScores()));
        });

        return scores;
    }
}
