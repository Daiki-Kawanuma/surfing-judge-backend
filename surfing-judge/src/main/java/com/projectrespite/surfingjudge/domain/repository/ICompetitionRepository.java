package com.projectrespite.surfingjudge.domain.repository;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;

import java.util.List;

public interface ICompetitionRepository {

    List<CompetitionEntity> getCompetitionByRound(int round);
    List<CompetitionEntity> getCompetitionByRoundHeat(int round, int heat);

}
