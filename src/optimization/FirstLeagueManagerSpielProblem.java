package optimization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import IO.IOHelper;

import com.google.inject.Inject;

public class FirstLeagueManagerSpielProblem implements ManagerSpielProblem {

	private List<Player> players;
	private List<Player> excludedPlayers;
	private List<Player> includedPlayers;

	@Inject
	public  FirstLeagueManagerSpielProblem() {
		players = IOHelper.loadPlayersFromCSV("spieler_ersteliga.csv");
		excludedPlayers = IOHelper.loadPlayersFromCSV("exclusion_ersteliga.csv");
		includedPlayers = IOHelper.loadPlayersFromCSV("inclusion_ersteliga.csv");

	}
	
	@Override
	public Collection<Player> getPlayers() {
		return players;
	}

	@Override
	public Collection<Player> getExcludedPlayers() {
		return excludedPlayers;
	}

	@Override
	public Collection<Player> getIncludedPlayers() {
		return includedPlayers;
	}
	
	public Collection<Player> getWinterPlayers() {
		return new ArrayList<Player>();
	}

}
