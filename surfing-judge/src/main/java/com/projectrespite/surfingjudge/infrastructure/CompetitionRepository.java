package com.projectrespite.surfingjudge.infrastructure;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.repository.ICompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

@Repository
@AllArgsConstructor
public class CompetitionRepository implements ICompetitionRepository {

    private CloudantClient client;

    @Override
    public List<CompetitionEntity> getCompetitionByRound(int round) {

        return client.database("competition", false)
                .query(new QueryBuilder(eq("round", round))
                        .fields("_id", "_rev", "player_number", "player_name", "player_color", "round", "heat")
                        .build(), CompetitionEntity.class)
                .getDocs();
    }

    @Override
    public List<CompetitionEntity> getCompetitionByRoundHeat(int round, int heat) {

        return client.database("competition", false)
                .query(new QueryBuilder(and(eq("round", round), eq("heat", heat)))
                        .fields("_id", "_rev", "player_number", "player_name", "player_color", "round", "heat")
                        .build(), CompetitionEntity.class)
                .getDocs();
    }

    @Override
    public void saveCompetitionByRoundHeat(CompetitionEntity entity) {

        client.database("competition", false)
                .save(entity);
    }
}
