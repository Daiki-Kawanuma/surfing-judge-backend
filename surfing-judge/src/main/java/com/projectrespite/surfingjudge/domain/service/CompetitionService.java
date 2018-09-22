package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.repository.ICompetitionRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class CompetitionService {

    private ICompetitionRepository competitionRepository;
    private ScoreService scoreService;

    public List<CompetitionEntity> getCompetitionByRoundHeat(int round, int heat){

        return competitionRepository.getCompetitionByRoundHeat(round, heat);
    }

    public List<CompetitionEntity> getCompetitionByRound(int round){

        return competitionRepository.getCompetitionByRound(round);
    }

    public void completeCompetitionByRoundHeat(int round, int heat){

        val scores = scoreService.getScores(round, heat);
        scores.sort(Comparator.comparing(ScoreResponse::getAggregate).reversed());

        if(round == 1 && heat == 1){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Red", 2, 1));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "White", 2, 2));

        } else if(round == 1 && heat == 2){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Red", 2, 2));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "White", 2, 1));

        } else if(round == 1 && heat == 3){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Red", 2, 3));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Blue", 2, 1));

        } else if(round == 1 && heat == 4){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Red", 2, 4));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "White", 2, 3));

        } else if(round == 1 && heat == 5){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Yellow", 2, 1));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "White", 2, 4));

        } else if(round == 1 && heat == 6){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Yellow", 2, 2));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Blue", 2, 4));

        } else if(round == 1 && heat == 7){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Yellow", 2, 3));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Blue", 2, 2));

        } else if(round == 1 && heat == 8){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Yellow", 2, 4));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Blue", 2, 3));

        } else if(round == 2 && heat == 1){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Red", 3, 1));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "White", 3, 2));

        } else if(round == 2 && heat == 2){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Red", 3, 2));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "White", 3, 1));

        } else if(round == 2 && heat == 3){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Yellow", 3, 1));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Blue", 3, 2));

        } else if(round == 2 && heat == 4){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Yellow", 3, 2));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Blue", 3, 1));

        } else if(round == 3 && heat == 1){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Red", 4, 1));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Yellow", 4, 1));

        } else if(round == 3 && heat == 2){

            var score = scores.get(0);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "White", 4, 1));

            score = scores.get(1);
            competitionRepository.saveCompetitionByRoundHeat(
                    new CompetitionEntity(null, null, score.getPlayerNumber(), score.getPlayerName(),
                            "Blue", 4, 1));

        }
    }
}
