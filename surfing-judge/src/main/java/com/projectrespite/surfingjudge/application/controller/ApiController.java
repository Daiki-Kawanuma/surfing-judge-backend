package com.projectrespite.surfingjudge.application.controller;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Sort;
import com.projectrespite.surfingjudge.domain.model.data.*;
import com.projectrespite.surfingjudge.domain.model.request.JudgeListRequest;
import com.projectrespite.surfingjudge.domain.model.response.JudgeNumberResponse;
import com.projectrespite.surfingjudge.domain.model.response.JudgeResponse;
import lombok.val;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Expression.gt;
import static com.cloudant.client.api.query.Operation.and;

@RestController
public class ApiController {

    @Autowired
    private CloudantClient client;

    /*@PutMapping(value = "/judges", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postJudges(@RequestBody JudgeListRequest form) {

        var database = client.database("judges", false);

        int i[] = {1};

        form.getScores().forEach(score -> {

            var idRev = database.query(new QueryBuilder(and(
                    eq("round", form.getRound()),
                    eq("heat", form.getHeat()),
                    eq("player_number", form.getPlayerNumber()),
                    eq("judge_number", i[0]),
                    eq("wave", form.getWave())))
                    .fields("id", "rev")
                    .build(), IdRev.class)
                    .getDocs().get(0);

            var judge = new JudgeEntity();
            judge.setId(idRev.get_id());
            judge.setRev(idRev.get_rev());
            judge.setRound(form.getRound());
            judge.setHeat(form.getHeat());
            judge.setPlayerNumber(form.getPlayerNumber());
            judge.setPlayerName(form.getName());
            judge.setJudgeNumber(i[0]++);
            judge.setWave(form.getWave());
            judge.setScore(score);
            database.update(judge);
        });

        return ResponseEntity.ok().body(null);
    }*/

    @PutMapping("/judge-number")
    public ResponseEntity putJudgeNumber() {

        val database = client.database("judge_number", false);
        val judgeNumber = database.query(new QueryBuilder(gt("id", "0"))
                .fields("id", "rev", "numbers")
                .build(), JudgeNumber.class)
                .getDocs().get(0);

        if (judgeNumber.getNumbers().size() < 5) {

            for (String s : Arrays.asList("1", "2", "3", "4", "5")) {

                if (!judgeNumber.getNumbers().contains(s)) {

                    judgeNumber.getNumbers().add(s);
                    Collections.sort(judgeNumber.getNumbers());
                    database.update(judgeNumber);
                    return ResponseEntity.ok()
                            .body(new JudgeNumberResponse("success", s));
                }
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JudgeNumberResponse("failure", null));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JudgeNumberResponse("failure", null));
        }
    }

    @DeleteMapping("/judge-number/{number}")
    public ResponseEntity deleteJudgeNumber(@PathVariable String number) {

        val database = client.database("judge_number", false);
        val judgeNumber = database.query(new QueryBuilder(gt("id", "0"))
                .fields("id", "rev", "numbers")
                .build(), JudgeNumber.class)
                .getDocs().get(0);

        if (judgeNumber.getNumbers().contains(number)) {
            judgeNumber.getNumbers().remove(number);
            database.update(judgeNumber);
        }

        return ResponseEntity.ok()
                .body(new JudgeNumberResponse("success", null));
    }
}