package com.projectrespite.surfingjudge.domain.repository;

import com.projectrespite.surfingjudge.domain.model.data.JudgeNumber;

import java.util.Optional;

public interface IJudgeNumberRepository {

    Optional<JudgeNumber> getJudgeNumber();
    void update(JudgeNumber judgeNumber);
}
