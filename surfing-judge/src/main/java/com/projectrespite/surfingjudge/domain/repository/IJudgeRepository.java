package com.projectrespite.surfingjudge.domain.repository;

import com.projectrespite.surfingjudge.domain.model.data.Judge;

import java.util.List;

public interface IJudgeRepository {

    List<Judge> findJudgeByRoundHeat(int round, int heat);
}
