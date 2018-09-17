package com.projectrespite.surfingjudge.infrastructure;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Sort;
import com.projectrespite.surfingjudge.domain.model.response.PlayerResponse;
import com.projectrespite.surfingjudge.domain.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cloudant.client.api.query.Expression.gt;

@Repository
public class PlayerRepository implements IPlayerRepository {

    @Autowired
    private CloudantClient client;

    public List<PlayerResponse> getPlayers() {

        return client.database("players", false)
                .query(new QueryBuilder(gt("_id", "0"))
                        .sort(Sort.asc("player_number"))
                        .fields("player_number", "player_name")
                        .build(), PlayerResponse.class)
                .getDocs();
    }
}
