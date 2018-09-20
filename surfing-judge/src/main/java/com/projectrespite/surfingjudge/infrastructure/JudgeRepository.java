package com.projectrespite.surfingjudge.infrastructure;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Sort;
import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.repository.IJudgeRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

@Repository
@AllArgsConstructor
public class JudgeRepository implements IJudgeRepository {

    private CloudantClient client;

    @Override
    public List<JudgeEntity> findJudgeByRoundHeat(int round, int heat) {

        return client.database("judges", false)
                .query(new QueryBuilder(and(
                        eq("round", round), eq("heat", heat)))
                        .sort(Sort.asc("player_number"), Sort.asc("judge_number"))
                        .fields("player_number", "player_name", "wave", "score", "judge_number")
                        .build(), JudgeEntity.class)
                .getDocs();
    }

    @Override
    public Optional<JudgeEntity> findByParams(int round, int heat, int playerNumber, int judgeNumber, int wave) {

        val entities = client.database("judges", false)
                .query(new QueryBuilder(and(
                        eq("round", round),
                        eq("heat", heat),
                        eq("player_number", playerNumber),
                        eq("judge_number", judgeNumber),
                        eq("wave", wave)))
                        .fields("_id", "_rev")
                        .build(), JudgeEntity.class)
                .getDocs();

        if (entities.size() == 0)
            return Optional.empty();
        else
            return Optional.of(entities.get(0));
    }

    @Override
    public JudgeEntity updateEntity(JudgeEntity entity) {

        client.database("judges", false).update(entity);
        return entity;
    }

    @Override
    public JudgeEntity saveEntity(JudgeEntity entity) {

        client.database("judges", false).post(entity);
        return entity;
    }
}
