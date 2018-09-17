package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.service.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScoreController {

    private ScoreService service;

    @GetMapping("/scores/{round}/{heat}")
    public List<ScoreResponse> getScores(@PathVariable int round,
                                         @PathVariable int heat) {

        return service.getScores(round, heat);
    }
}
