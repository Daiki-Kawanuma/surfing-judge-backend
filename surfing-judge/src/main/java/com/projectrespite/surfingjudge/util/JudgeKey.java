package com.projectrespite.surfingjudge.util;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;

import java.util.function.Function;

public class JudgeKey implements Function<JudgeEntity, String> {

    @Override
    public String apply(JudgeEntity judgeEntity) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(judgeEntity.getPlayerNumber()).append("-").append(judgeEntity.getWave());
        return buffer.toString();
    }
}
