package com.projectrespite.surfingjudge.infrastructure;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.projectrespite.surfingjudge.domain.model.data.JudgeNumber;
import com.projectrespite.surfingjudge.domain.repository.IJudgeNumberRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.cloudant.client.api.query.Expression.gt;

@Repository
@AllArgsConstructor
public class JudgeNumberRepository implements IJudgeNumberRepository {

    private CloudantClient client;

    @Override
    public Optional<JudgeNumber> getJudgeNumber(){

        val judgeNumbers = client.database("judge_number", false)
                .query(new QueryBuilder(gt("_id", "0"))
                        .fields("_id", "_rev", "numbers")
                        .build(), JudgeNumber.class)
                .getDocs();

        if(judgeNumbers.size() > 0)
            return Optional.of(judgeNumbers.get(0));
        else
            return  Optional.empty();
    }

    @Override
    public void update(JudgeNumber judgeNumber) {

        client.database("judge_number", false)
                .update(judgeNumber);
    }
}
