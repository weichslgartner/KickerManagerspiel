package optimization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import IO.IOHelper;

public class PlayerSelection extends HashSet<Player> {
	private static final long serialVersionUID = 1L;
	private boolean feasible;

	public boolean isFeasible() {
		return feasible;
	}

	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	@Override
	public String toString() {
		return getTeamSortedByPosAndPoints(false);
	}

	
	public String toCSVString() {
		return getTeamSortedByPosAndPoints(true);
	}
	
	
	/**
	 * sorts the PlayerSelection first by the position (TOR-ABW-MIT-STU) and the
	 * by descending player points
	 * 
	 * @return sorted string of PlayerSelection
	 */
	public String getTeamSortedByPosAndPoints(boolean csv) {
		Map<String, Collection<Player>> posMap = IOHelper
				.splitlistByPositions(this);
		String curTeam = "";
		for (String pos : Positions.POSITIONSARRAY) {
			List<Player> posList = new ArrayList<Player>(posMap.get(pos));
			Collections.sort(posList,
					(p1, p2) -> p2.getPoints1516() - p1.getPoints1516());
			for (Player player : posList) {
				if(csv){
					curTeam += player.toCSVString();
				}else{
					curTeam += player.toString() + " ";
				}
				
			}
		}
		return curTeam;
	}

	public double getTeamValue() {
		return this.stream().mapToDouble(p -> p.getValue()).sum();
	}

}
