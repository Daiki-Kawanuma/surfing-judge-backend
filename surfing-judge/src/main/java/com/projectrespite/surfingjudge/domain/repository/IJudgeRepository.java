package com.projectrespite.surfingjudge.domain.repository;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;

import java.util.List;

public interface IJudgeRepository {

    List<JudgeEntity> findJudgeByRoundHeat(int round, int heat);
}
