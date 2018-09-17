package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.response.JudgeResponse;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.service.JudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "ジャッジ", description = "ジャッジを取得する・登録する")
@AllArgsConstructor
public class JudgeController {

    private JudgeService service;

    @GetMapping("/judges/{round}/{heat}")
    @ApiOperation(value = "ジャッジ取得", produces = "application/json", response = ScoreResponse.class, responseContainer = "List")
    public List<JudgeResponse> getJudgeAggregate(@ApiParam(value = "ラウンド", required = true) @PathVariable int round,
                                                 @ApiParam(value = "ヒート", required = true) @PathVariable int heat) {

        return service.getJudges(round, heat);
    }
}
