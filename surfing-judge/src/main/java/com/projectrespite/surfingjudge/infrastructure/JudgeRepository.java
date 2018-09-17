package com.projectrespite.surfingjudge.infrastructure;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Sort;
import com.projectrespite.surfingjudge.domain.model.data.Judge;
import com.projectrespite.surfingjudge.domain.repository.IJudgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

@Repository
@AllArgsConstructor
public class JudgeRepository implements IJudgeRepository {

    private CloudantClient client;

    @Override
    public List<Judge> findJudgeByRoundHeat(int round, int heat) {

        return client.database("judges", false)
                .query(new QueryBuilder(and(
                        eq("round", round), eq("heat", heat)))
                        .sort(Sort.asc("player_number"))
                        .fields("player_number", "name", "wave", "score", "judge_number")
                        .build(), Judge.class)
                .getDocs();
    }
}
