package optimization;
import java.util.Collection;
import java.util.List;

import IO.IOHelper;

import com.google.inject.Inject;

public class FirstLeagueManagerSpielProblemWinter implements ManagerSpielProblem {

	private List<Player> players;
	private List<Player> excludedPlayers;
	private List<Player> includedPlayers;
	private List<Player> winterPlayers;

	@Inject
	public  FirstLeagueManagerSpielProblemWinter() {
		players = IOHelper.loadPlayersFromCSV("spieler_ersteliga_winter.csv");
		excludedPlayers = IOHelper.loadPlayersFromCSV("exclusion_ersteliga.csv");
		includedPlayers = IOHelper.loadPlayersFromCSV("inclusion_ersteliga.csv");
		winterPlayers = IOHelper.loadPlayersFromCSV("current_clean.csv");

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
	
	@Override
	public Collection<Player> getWinterPlayers() {
		return winterPlayers;
	}

}
