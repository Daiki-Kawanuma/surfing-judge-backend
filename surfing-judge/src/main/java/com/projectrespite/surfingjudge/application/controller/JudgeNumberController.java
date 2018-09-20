package com.projectrespite.surfingjudge.application.controller;

import com.projectrespite.surfingjudge.domain.model.response.JudgeNumberResponse;
import com.projectrespite.surfingjudge.domain.service.JudgeNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "ジャッジ番号", description = "ジャッジ番号を登録する")
@AllArgsConstructor
public class JudgeNumberController {

    private JudgeNumberService service;

    @PutMapping("/judge-number")
    @ApiOperation(value = "ジャッジ番号登録", produces = "application/json", response = JudgeNumberResponse.class)
    public ResponseEntity<JudgeNumberResponse> putJudgeNumber() {

        val s = service.updateJudgeNumber();

        return ResponseEntity.ok().body(new JudgeNumberResponse("success", s));
    }

    @DeleteMapping("/judge-number/{number}")
    @ApiOperation(value = "ジャッジ番号削除", produces = "application/json", response = JudgeNumberResponse.class)
    public ResponseEntity deleteJudgeNumber(@PathVariable String number) {

        service.deleteJudgeNumber(number);

        return ResponseEntity.accepted().build();
    }
}
