package com.projectrespite.surfingjudge.domain.repository;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;

import java.util.List;
import java.util.Optional;

public interface ICompetitionRepository {

    List<CompetitionEntity> getCompetitionByRound(int round);
    List<CompetitionEntity> getCompetitionByRoundHeat(int round, int heat);
    Optional<CompetitionEntity> getCompetitionByEntity(CompetitionEntity entity);
    void save(CompetitionEntity entity);
    void saveOrUpdate(CompetitionEntity entity);
}
