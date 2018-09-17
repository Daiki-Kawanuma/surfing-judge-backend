package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "スコア", description = "スコアを取得する")
@AllArgsConstructor
public class ScoreController {

    private ScoreService service;

    @GetMapping("/scores/{round}/{heat}")
    @ApiOperation(value = "スコア取得", produces = "application/json", response = ScoreResponse.class, responseContainer = "List")
    public List<ScoreResponse> getScores(@ApiParam(value = "ラウンド", required = true) @PathVariable int round,
                                         @ApiParam(value = "ヒート", required = true) @PathVariable int heat) {

        return service.getScores(round, heat);
    }
}
