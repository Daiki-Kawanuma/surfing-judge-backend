package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.request.PlayerScoreRequest;
import com.projectrespite.surfingjudge.domain.model.response.JudgeResponse;
import com.projectrespite.surfingjudge.domain.model.response.JudgedWaveResponse;
import com.projectrespite.surfingjudge.domain.repository.ICompetitionRepository;
import com.projectrespite.surfingjudge.domain.repository.IJudgeRepository;
import com.projectrespite.surfingjudge.util.JudgeConverter;
import com.projectrespite.surfingjudge.util.JudgeKey;
import com.projectrespite.surfingjudge.util.JudgedWaveConverter;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JudgeService {

    private IJudgeRepository judgeRepository;
    private ICompetitionRepository competitionRepository;

    public List<JudgeResponse> getJudges(int round, int heat) {

        val judges = judgeRepository.findJudgeByRoundHeat(round, heat);

        Map<String, List<JudgeEntity>> groupByPlayer = judges.stream()
                .collect(Collectors.groupingBy(new JudgeKey()));

        return groupByPlayer.entrySet().stream()
                .map(Map.Entry::getValue)
                .map(new JudgeConverter())
                .collect(Collectors.toList());
    }

    public List<JudgedWaveResponse> getJudgedWave(int round, int heat, int judgeNumber) {

        val judges = judgeRepository.findJudgeByRoundHeatJudgeNumber(round, heat, judgeNumber);
        val competitions = competitionRepository.getCompetitionByRoundHeat(round,heat);

        return competitions.stream()
                .map(new JudgedWaveConverter(judges))
                .collect(Collectors.toList());
    }

    public JudgeEntity updateEntity(JudgeEntity entity) {

        val optional = judgeRepository.findByParams(entity.getRound(), entity.getHeat(),
                entity.getPlayerNumber(), entity.getJudgeNumber(), entity.getWave());

        if (optional.isPresent()) {

            entity.setId(optional.get().getId());
            entity.setRev(optional.get().getRev());
            judgeRepository.updateEntity(entity);
            return entity;

        } else {
            judgeRepository.saveEntity(entity);
            return entity;
        }
    }

    public void updateList(PlayerScoreRequest request) {

        // ラムダ式でインデックスを使うために使用
        int i[] = {1};

        request.getScores().forEach(score -> {

            val optional = judgeRepository.findByParams(request.getRound(), request.getHeat(),
                    request.getPlayerNumber(), i[0], request.getWave());

            if (optional.isPresent()) {

                val entity = new JudgeEntity();
                entity.setId(optional.get().getId());
                entity.setRev(optional.get().getRev());
                entity.setRound(request.getRound());
                entity.setHeat(request.getHeat());
                entity.setPlayerNumber(request.getPlayerNumber());
                entity.setPlayerName(request.getPlayerName());
                entity.setPlayerColor(request.getPlayerColor());
                entity.setJudgeNumber(i[0]);
                entity.setWave(request.getWave());
                entity.setScore(score);

                judgeRepository.updateEntity(entity);

            }

            i[0]++;
        });
    }
}
