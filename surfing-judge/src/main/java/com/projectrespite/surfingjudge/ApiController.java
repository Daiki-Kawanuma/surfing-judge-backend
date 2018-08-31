package com.projectrespite.surfingjudge;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Sort;
import com.projectrespite.surfingjudge.model.*;
import com.projectrespite.surfingjudge.model.form.JudgeListForm;
import com.projectrespite.surfingjudge.model.form.LoginForm;
import com.projectrespite.surfingjudge.model.response.LoginResponse;
import lombok.val;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Expression.gt;
import static com.cloudant.client.api.query.Operation.and;

@RestController
public class ApiController {

    @Autowired
    private CloudantClient client;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse doLogin(@RequestBody LoginForm form) {

        var response = new LoginResponse();

        switch (form.getPassword()) {
            case "player":
                response.setRole("player");
                break;
            case "judge":
                response.setRole("judge");
                break;
            default:
                response.setStatus("failure");
                break;
        }

        return response;
    }

    @GetMapping(value = "/players")
    public List<String> getPlayers() {

        var players = new ArrayList<String>();
        players.add("Masaki Goda");
        players.add("Daiki Kawanuma");

        return players;
    }

    @GetMapping("/scores/{round}/{heat}")
    public List<Score> getScores(@PathVariable int round,
                                 @PathVariable int heat) {

        Database database = client.database("judges", false);
        List<Judge> judges = database.query(new QueryBuilder(and(
                eq("round", round), eq("heat", heat)))
                .sort(Sort.asc("player_number"))
                .fields("player_number", "name", "wave", "score", "judge_number")
                .build(), Judge.class)
                .getDocs();

        var scores = new ArrayList<Score>();

        judges.forEach(judge -> {

            System.out.println(judge);

            var optional = scores.stream()
                    .filter(s -> s.getPlayerNumber() == judge.getPlayerNumber())
                    .findFirst();

            if (optional.isPresent()) {

                var score = optional.get();
                score.getJudgedScores()[judge.getWave() - 1][judge.getJudgeNumber() - 1]
                        = judge.getScore();

            } else {

                var score = new Score();
                score.setPlayerNumber(judge.getPlayerNumber());
                score.setName(judge.getName());
                score.setScores(new ArrayList<Double>());
                score.getJudgedScores()[judge.getWave() - 1][judge.getJudgeNumber() - 1]
                        = judge.getScore();

                scores.add(score);
            }
        });

        scores.forEach(score -> {

            Arrays.stream(score.getJudgedScores()).forEach(array -> {
                if (Arrays.stream(array).sum() > 0) {
                    score.getScores().add(AggregateUtil.avarage(array));
                }
            });

            score.setAggregate(AggregateUtil.sumBestAndSecondBest(score.getScores()));
            score.setJudgedScores(null);
        });

        return scores;
    }

    @GetMapping("/judges/{round}/{heat}")
    public List<JudgeAggregate> getJudges(@PathVariable int round,
                                          @PathVariable int heat) {

        var database = client.database("judges", false);
        var judges = database.query(new QueryBuilder(and(
                eq("round", round), eq("heat", heat)))
                .sort(Sort.asc("player_number"), Sort.asc("judge_number"))
                .fields("player_number", "name", "judge_number", "wave", "score")
                .build(), Judge.class)
                .getDocs();

        var aggregates = new ArrayList<JudgeAggregate>();

        judges.forEach(judge -> {

            var optional = aggregates.stream()
                    .filter(a -> a.getPlayerNumber() == judge.getPlayerNumber())
                    .filter(a -> a.getWave() == judge.getWave())
                    .findFirst();

            if (optional.isPresent()) {

                optional.get().getScores().add(judge.getScore());

            } else {

                var aggregate = new JudgeAggregate();
                aggregate.setPlayerNumber(judge.getPlayerNumber());
                aggregate.setName(judge.getName());
                aggregate.setWave(judge.getWave());
                aggregate.setScores(new ArrayList<Double>());
                aggregate.getScores().add(judge.getScore());

                aggregates.add(aggregate);
            }
        });

        return aggregates;
    }

    @PutMapping(value = "/judge", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Judge> postJudge(@RequestBody Judge judge) {

        var database = client.database("judges", false);


        try {

            var idRev = database.query(new QueryBuilder(and(
                    eq("round", judge.getRound()),
                    eq("heat", judge.getHeat()),
                    eq("player_number", judge.getPlayerNumber()),
                    eq("judge_number", judge.getJudgeNumber()),
                    eq("wave", judge.getWave())))
                    .fields("_id", "_rev")
                    .build(), IdRev.class)
                    .getDocs().get(0);

            judge.set_id(idRev.get_id());
            judge.set_rev(idRev.get_rev());
            database.update(judge);

        } catch (IndexOutOfBoundsException e) {

            database.post(judge);
        }

        return ResponseEntity.ok()
                .body(judge);
    }

    @PutMapping(value = "/judges", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postJudges(@RequestBody JudgeListForm form) {

        var database = client.database("judges", false);

        int i[] = {1};

        form.getScores().forEach(score -> {

            var idRev = database.query(new QueryBuilder(and(
                    eq("round", form.getRound()),
                    eq("heat", form.getHeat()),
                    eq("player_number", form.getPlayerNumber()),
                    eq("judge_number", i[0]),
                    eq("wave", form.getWave())))
                    .fields("_id", "_rev")
                    .build(), IdRev.class)
                    .getDocs().get(0);

            var judge = new Judge();
            judge.set_id(idRev.get_id());
            judge.set_rev(idRev.get_rev());
            judge.setRound(form.getRound());
            judge.setHeat(form.getHeat());
            judge.setPlayerNumber(form.getPlayerNumber());
            judge.setName(form.getName());
            judge.setJudgeNumber(i[0]++);
            judge.setWave(form.getWave());
            judge.setScore(score);
            database.update(judge);
        });

        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/judge-number")
    public ResponseEntity getJudgeNumber() {

        val database = client.database("judge_number", false);
        val count = database.query(new QueryBuilder(gt("_id", "0"))
                .fields("_id", "_rev", "count")
                .build(), Count.class)
                .getDocs().get(0);

        if (count.getCount() < 5) {
            count.setCount(count.getCount() + 1);
            database.update(count);
            return ResponseEntity.ok().body(count);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
