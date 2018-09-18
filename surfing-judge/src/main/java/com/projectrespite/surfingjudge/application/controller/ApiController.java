package com.projectrespite.surfingjudge.application.controller;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.query.QueryBuilder;
import com.projectrespite.surfingjudge.domain.model.data.*;
import com.projectrespite.surfingjudge.domain.model.response.JudgeNumberResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.cloudant.client.api.query.Expression.gt;

@RestController
public class ApiController {

    @Autowired
    private CloudantClient client;

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