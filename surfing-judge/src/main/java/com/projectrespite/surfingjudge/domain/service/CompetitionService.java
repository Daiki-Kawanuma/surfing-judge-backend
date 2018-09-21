package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.repository.ICompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    private ICompetitionRepository repository;

    public List<CompetitionEntity> getCompetitionByRoundHeat(int round, int heat){

        return repository.getCompetitionByRoundHeat(round, heat);
    }

    public List<CompetitionEntity> getCompetitionByRound(int round){

        return repository.getCompetitionByRound(round);
    }
}
