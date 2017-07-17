package optimization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;
import org.opt4j.core.start.Constant;

import IO.IOHelper;

import com.google.inject.Inject;

import static org.opt4j.core.Objective.Sign.*;

public class ManagerSpielEvaluator implements Evaluator<PlayerSelection> {
	boolean tactics343;
	boolean tactics352;
	boolean tactics433;
	boolean tactics442;
	boolean tactics451;
	private double budget;

	@Inject
	public ManagerSpielEvaluator(@Constant(value = "3-4-3") boolean tactics343,
			@Constant(value = "3-5-2") boolean tactics352,
			@Constant(value = "4-3-3") boolean tactics433,
			@Constant(value = "4-4-2") boolean tactics442,
			@Constant(value = "4-5-1") boolean tactics451,
			@Constant(value = "teambudget") double budget) {
		this.tactics343 = tactics343;
		this.tactics352 = tactics352;
		this.tactics433 = tactics433;
		this.tactics442 = tactics442;
		this.tactics451 = tactics451;
		this.budget = budget;

	}

	@Override
	public Objectives evaluate(PlayerSelection playerSelection) {
		int points = 0;
		Objectives objectives = new Objectives();
		Map<String, Integer> clubCountStarting11 = new HashMap<String, Integer>();

		ArrayList<Player> playerList = new ArrayList<Player>(playerSelection);
		Collections.sort(playerList,
				(p1, p2) -> p2.getPoints1516() - p1.getPoints1516());
		List<Player> starting11 = playerList.subList(0, 11);
		starting11.forEach(player -> increaseClubCount(clubCountStarting11,
				player));
		// max 3 players of same team in starting 11
		playerSelection.setFeasible(Collections.max(clubCountStarting11
				.values()) <= 3);

		Map<String, Collection<Player>> posMap = IOHelper
				.splitlistByPositions(playerSelection);
		int points451 = 0;
		int points352 = 0;
		int points442 = 0;
		int points433 = 0;
		int points343 = 0;
		float value = 0;

		for (String pos : Positions.POSITIONSARRAY) {
			int i = 0;
			List<Player> curPosCollection = (List<Player>) posMap.get(pos);
			Collections.sort(curPosCollection, (p1, p2) -> p2.getPoints1516()
					- p1.getPoints1516());
			for (Player player : curPosCollection) {
				if (pos.equals(Positions.TOR) && i < 1) {
					points451 += player.getPoints1516();
					;
					points352 += player.getPoints1516();
					points442 += player.getPoints1516();
					points433 += player.getPoints1516();
					points343 += player.getPoints1516();

				}
				if (pos.equals(Positions.ABW)) {
					if (i < 3) {
						points352 += player.getPoints1516();
						points343 += player.getPoints1516();
					}
					if (i < 4) {
						points451 += player.getPoints1516();
						;
						points442 += player.getPoints1516();
						points433 += player.getPoints1516();
					}
				}

				if (pos.equals(Positions.MIT)) {
					if (i < 3) {
						points433 += player.getPoints1516();
					}
					if (i < 4) {
						points442 += player.getPoints1516();
						points343 += player.getPoints1516();
					}
					if (i < 5) {
						points352 += player.getPoints1516();
						points451 += player.getPoints1516();
						;
					}
				}

				if (pos.equals(Positions.STU)) {
					if (i < 1) {
						points451 += player.getPoints1516();

					}
					if (i < 2) {
						points442 += player.getPoints1516();
						points352 += player.getPoints1516();
					}
					if (i < 5) {
						points433 += player.getPoints1516();
						points343 += player.getPoints1516();

					}
				}
				value += player.getValue();
				points += player.getPoints1516();
				i++;
			}
		}

		// System.out.println(points);
		if (value <= budget && playerSelection.isFeasible()) {
			objectives.add(new Objective("overall points", MAX), points);
			if (tactics451)
				objectives.add(new Objective("451", MAX), points451);
			if (tactics352)
				objectives.add(new Objective("352", MAX), points352);
			if (tactics442)
				objectives.add(new Objective("442", MAX), points442);
			if (tactics433)
				objectives.add(new Objective("433", MAX), points433);
			if (tactics343)
				objectives.add(new Objective("343", MAX), points343);
		} else {
			playerSelection.setFeasible(false);
			objectives.add(new Objective("overall points", MAX),
					Objective.INFEASIBLE);
			if (tactics451)
				objectives.add(new Objective("451", MAX), Objective.INFEASIBLE);
			if (tactics352)
				objectives.add(new Objective("352", MAX), Objective.INFEASIBLE);
			if (tactics442)
				objectives.add(new Objective("442", MAX), Objective.INFEASIBLE);
			if (tactics433)
				objectives.add(new Objective("433", MAX), Objective.INFEASIBLE);
			if (tactics343)
				objectives.add(new Objective("343", MAX), Objective.INFEASIBLE);
		}

		return objectives;
	}

	private void increaseClubCount(Map<String, Integer> clubCountStarting11,
			Player player) {
		if (!clubCountStarting11.containsKey(player.getClub())) {
			clubCountStarting11.put(player.getClub(), 1);
		} else {
			clubCountStarting11.put(player.getClub(),
					clubCountStarting11.get(player.getClub()) + 1);
		}
	}

}
