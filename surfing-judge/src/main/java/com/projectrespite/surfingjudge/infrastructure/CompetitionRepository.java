package com.projectrespite.surfingjudge.infrastructure;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Sort;
import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.repository.ICompetitionRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                        .sort(Sort.asc("player_number"))
                        .build(), CompetitionEntity.class)
                .getDocs();
    }

    @Override
    public List<CompetitionEntity> getCompetitionByRoundHeat(int round, int heat) {

        return client.database("competition", false)
                .query(new QueryBuilder(and(eq("round", round), eq("heat", heat)))
                        .fields("_id", "_rev", "player_number", "player_name", "player_color", "round", "heat")
                        .sort(Sort.asc("player_number"))
                        .build(), CompetitionEntity.class)
                .getDocs();
    }

    @Override
    public Optional<CompetitionEntity> getCompetitionByEntity(CompetitionEntity entity) {

        val competitions = client.database("competition", false)
                .query(new QueryBuilder(and(eq("round", entity.getRound()),
                        eq("heat", entity.getHeat()),
                        eq("player_color", entity.getPlayerColor())))
                        .fields("_id", "_rev", "player_number", "player_name", "player_color", "round", "heat")
                        .build(), CompetitionEntity.class)
                .getDocs();

        if(competitions.isEmpty())
            return Optional.empty();
        else
            return Optional.of(competitions.get(0));

    }

    @Override
    public void save(CompetitionEntity entity) {

        client.database("competition", false)
                .save(entity);
    }

    @Override
    public void saveOrUpdate(CompetitionEntity entity) {

        val optional = getCompetitionByEntity(entity);

        if(optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setRev(optional.get().getRev());

            client.database("competition", false)
                    .update(entity);
        } else {
            client.database("competition", false)
                    .save(entity);
        }
    }
}
