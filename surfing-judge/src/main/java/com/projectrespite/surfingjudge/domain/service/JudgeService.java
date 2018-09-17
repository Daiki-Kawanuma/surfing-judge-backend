package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.JudgeEntity;
import com.projectrespite.surfingjudge.domain.model.response.JudgeResponse;
import com.projectrespite.surfingjudge.domain.repository.IJudgeRepository;
import com.projectrespite.surfingjudge.util.JudgeConverter;
import com.projectrespite.surfingjudge.util.JudgeKey;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JudgeService {

    private IJudgeRepository repository;

    public List<JudgeResponse> getJudges(int round, int heat) {

        val judges = repository.findJudgeByRoundHeat(round, heat);

        Map<String, List<JudgeEntity>> groupByPlayer = judges.stream()
                .collect(Collectors.groupingBy(new JudgeKey()));

        return groupByPlayer.entrySet().stream()
                .map(Map.Entry::getValue)
                .map(new JudgeConverter())
                .collect(Collectors.toList());
    }
}
