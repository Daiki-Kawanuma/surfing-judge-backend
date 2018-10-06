package com.projectrespite.surfingjudge.domain.service;

import com.projectrespite.surfingjudge.domain.model.data.Color;
import com.projectrespite.surfingjudge.domain.model.data.CompetitionEntity;
import com.projectrespite.surfingjudge.domain.model.response.ScoreResponse;
import com.projectrespite.surfingjudge.domain.repository.ICompetitionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class CompetitionService {

	private ICompetitionRepository competitionRepository;

	private ScoreService scoreService;

	public List<CompetitionEntity> getCompetitionByRoundHeat(int round, int heat) {

		return competitionRepository.getCompetitionByRoundHeat(round, heat);
	}

	public List<CompetitionEntity> getCompetitionByRound(int round) {

		return competitionRepository.getCompetitionByRound(round);
	}

	public void completeCompetitionByRoundHeat(int round, int heat) {

		val roundHeat = RoundHeat.fromIntegerRoundHeat(round, heat);
		if (roundHeat == RoundHeat.ROUND4_1)
			return;

		val scores = scoreService.getScores(round, heat);
		scores.sort(Comparator.comparing(ScoreResponse::getAggregate).reversed());

		val rank1Score = scores.get(0);
		val rank1Next = roundHeat.rank1nextRoundHeat();
		competitionRepository.saveOrUpdate(
				new CompetitionEntity(
						null,
						null,
						rank1Score.getPlayerNumber(),
						rank1Score.getPlayerName(),
						rank1Next.getRight().getColor(),
						rank1Next.getLeft().getRound(),
						rank1Next.getLeft().getHeat()));

		val rank2Score = scores.get(1);
		val rank2Next = roundHeat.rank2nextRoundHeat();
		competitionRepository.saveOrUpdate(
				new CompetitionEntity(
						null,
						null,
						rank2Score.getPlayerNumber(),
						rank2Score.getPlayerName(),
						rank2Next.getRight().getColor(),
						rank2Next.getLeft().getRound(),
						rank2Next.getLeft().getHeat()));

	}

	@AllArgsConstructor
	@Getter
	enum RoundHeat {
		ROUND1_1(1, 1){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_1, Color.RED); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_2, Color.WHITE); }
		},
		ROUND1_2(1, 2){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_2, Color.RED); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_1, Color.WHITE); }
		},
		ROUND1_3(1, 3){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_3, Color.RED); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_1, Color.BLUE); }
		},
		ROUND1_4(1, 4){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_4, Color.RED); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_3, Color.WHITE); }
		},
		ROUND1_5(1, 5){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_1, Color.YELLOW); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_4, Color.WHITE); }
		},
		ROUND1_6(1, 6){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_2, Color.YELLOW); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_4, Color.BLUE); }
		},
		ROUND1_7(1, 7){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_3, Color.YELLOW); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_2, Color.BLUE); }
		},
		ROUND1_8(1, 8){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_4, Color.YELLOW); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND2_3, Color.BLUE); }
		},
		ROUND2_1(2, 1){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_1, Color.RED); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_2, Color.WHITE); }
		},
		ROUND2_2(2, 2){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_2, Color.RED); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_1, Color.WHITE); }
		},
		ROUND2_3(2, 3){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_1, Color.YELLOW); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_2, Color.BLUE); }
		},
		ROUND2_4(2, 4){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_2, Color.YELLOW); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND3_1, Color.BLUE); }
		},
		ROUND3_1(3, 1){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND4_1, Color.RED); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND4_1, Color.YELLOW); }
		},
		ROUND3_2(3, 2){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return Pair.of(RoundHeat.ROUND4_1, Color.WHITE); }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return Pair.of(RoundHeat.ROUND4_1, Color.BLUE); }
		},
		ROUND4_1(4, 1){
			@Override Pair<RoundHeat, Color> rank1nextRoundHeat(){ return null; }
			@Override Pair<RoundHeat, Color> rank2nextRoundHeat(){ return null; }
		};

		private int round;

		private int heat;

		abstract Pair<RoundHeat, Color> rank1nextRoundHeat();

		abstract Pair<RoundHeat, Color> rank2nextRoundHeat();

		public static RoundHeat fromIntegerRoundHeat(int round, int heat) {
			return Arrays
					.stream(RoundHeat.values())
					.filter(e -> e.getRound() == round)
					.filter(e -> e.getHeat() == heat)
					.findFirst().orElseThrow(IllegalArgumentException::new);
		}
	}
}


