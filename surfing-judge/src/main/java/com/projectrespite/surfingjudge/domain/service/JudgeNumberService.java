package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.repository.IJudgeNumberRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.Collections;

@Service
@AllArgsConstructor
public class JudgeNumberService {

    private IJudgeNumberRepository repository;

    public String updateJudgeNumber() {

        val optional = repository.getJudgeNumber();

        if (!optional.isPresent())
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error happen");

        val judgeNumber = optional.get();

        if (judgeNumber.getNumbers().size() >= 5)
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Judges already enough");

        for (String s : Arrays.asList("1", "2", "3", "4", "5")) {

            if (!judgeNumber.getNumbers().contains(s)) {

                judgeNumber.getNumbers().add(s);
                Collections.sort(judgeNumber.getNumbers());
                repository.update(judgeNumber);

                return s;
            }
        }

        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error happen");
    }

    public void deleteJudgeNumber(String number){

        val optional = repository.getJudgeNumber();

        if (!optional.isPresent())
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error happen");

        val judgeNumber = optional.get();

        if (!judgeNumber.getNumbers().contains(number))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Judge number not found");

        judgeNumber.getNumbers().remove(number);
        repository.update(judgeNumber);
    }
}
