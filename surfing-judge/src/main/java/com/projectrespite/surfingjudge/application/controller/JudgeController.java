package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.request.PlayerScoreRequest;
import com.projectrespite.surfingjudge.domain.model.response.JudgeResponse;
import com.projectrespite.surfingjudge.domain.model.response.JudgedWaveResponse;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.service.JudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "ジャッジ", description = "ジャッジを取得する・登録する")
@AllArgsConstructor
public class JudgeController {

    private JudgeService service;

    @GetMapping("/judges/{round}/{heat}")
    @ApiOperation(value = "ジャッジ取得", produces = "application/json", response = ScoreResponse.class, responseContainer = "List")
    public List<JudgeResponse> getJudges(@ApiParam(value = "ラウンド", required = true) @PathVariable int round,
                                         @ApiParam(value = "ヒート", required = true) @PathVariable int heat) {

        return service.getJudges(round, heat);
    }

    @PutMapping(value = "/judges", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "ジャッジ登録", produces = "application/json", response = JudgeEntity.class)
    public JudgeEntity updateJudge(@RequestBody JudgeEntity entity) {

        return service.updateEntity(entity);
    }

    @PutMapping(value = "/judges/player-scores", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "ジャッジ一括更新", produces = "application/json", response = ResponseEntity.class)
    public ResponseEntity postJudges(@RequestBody PlayerScoreRequest request) {

        service.updateList(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/judges/{round}/{heat}/{judgeNumber}/judged-wave")
    @ApiOperation(value = "ジャッジ済みWave取得", produces = "application/json", response = JudgedWaveResponse.class, responseContainer = "List")
    public List<JudgedWaveResponse> getJudgedWave(@ApiParam(value = "ラウンド", required = true) @PathVariable int round,
                                                  @ApiParam(value = "ヒート", required = true) @PathVariable int heat,
                                                  @ApiParam(value = "ジャッジ番号", required = true) @PathVariable int judgeNumber) {

        return service.getJudgedWave(round, heat, judgeNumber);
    }
}
