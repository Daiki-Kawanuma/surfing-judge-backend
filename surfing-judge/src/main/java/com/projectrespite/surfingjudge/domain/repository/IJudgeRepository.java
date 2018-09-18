package com.projectrespite.surfingjudge.domain.repository;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;

import java.util.List;
import java.util.Optional;

public interface IJudgeRepository {

    List<JudgeEntity> findJudgeByRoundHeat(int round, int heat);

    Optional<JudgeEntity> findByParams(int round, int heat, int playerNumber, int judgeNumber, int wave);

    JudgeEntity updateEntity(JudgeEntity entity);

    JudgeEntity saveEntity(JudgeEntity entity);
}
