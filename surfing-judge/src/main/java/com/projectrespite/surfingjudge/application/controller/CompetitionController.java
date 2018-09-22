package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
@Api(tags = "対戦表", description = "対戦表を取得する")
@AllArgsConstructor
public class CompetitionController {

    private CompetitionService service;

    @GetMapping("/{round}")
    @ApiOperation(value = "対戦表取得", produces = "application/json", response = CompetitionEntity.class, responseContainer = "List")
    public List<CompetitionEntity> getCompetitionByRound(@ApiParam(value = "ラウンド", required = true) @PathVariable int round) {

        return service.getCompetitionByRound(round);
    }

    @GetMapping("/{round}/{heat}")
    @ApiOperation(value = "対戦表取得", produces = "application/json", response = CompetitionEntity.class, responseContainer = "List")
    public List<CompetitionEntity> getCompetitionByRoundHeat(@ApiParam(value = "ラウンド", required = true) @PathVariable int round,
                                                         @ApiParam(value = "ヒート", required = true) @PathVariable int heat) {

        return service.getCompetitionByRoundHeat(round, heat);
    }

    @PutMapping("/{round}/{heat}")
    @ApiOperation(value = "対戦終了", produces = "application/json", response = CompetitionEntity.class, responseContainer = "List")
    public void gcompleteCompetitionByRoundHeat(@ApiParam(value = "ラウンド", required = true) @PathVariable int round,
                                                                   @ApiParam(value = "ヒート", required = true) @PathVariable int heat) {

        service.completeCompetitionByRoundHeat(round, heat);
    }
}
