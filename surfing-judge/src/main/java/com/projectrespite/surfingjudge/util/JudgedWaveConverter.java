package com.projectrespite.surfingjudge.util;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.response.JudgedWaveResponse;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class JudgedWaveConverter implements Function<CompetitionEntity, JudgedWaveResponse> {

    private int judgeNumber;
    private List<JudgeEntity> judges;

    @Override
    public JudgedWaveResponse apply(CompetitionEntity entity) {

        val response = new JudgedWaveResponse();
        response.setPlayerNumber(entity.getPlayerNumber());
        response.setPlayerName(entity.getPlayerName());
        response.setPlayerColor(entity.getPlayerColor());
        response.setWave(judges.stream()
                .filter(j -> j.getJudgeNumber() == this.judgeNumber && j.getPlayerNumber() == entity.getPlayerNumber())
                .max(Comparator.comparing(JudgeEntity::getWave))
                .map(JudgeEntity::getWave).orElse(0));

        return response;
    }
}
