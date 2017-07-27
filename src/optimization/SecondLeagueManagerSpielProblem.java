package optimization;
import java.util.Collection;
import java.util.List;

import IO.IOHelper;

import com.google.inject.Inject;

public class SecondLeagueManagerSpielProblem implements ManagerSpielProblem {

	private List<Player> players;
	private List<Player> excludedPlayers;
	private List<Player> includedPlayers;

	@Inject
	public  SecondLeagueManagerSpielProblem() {
		players = IOHelper.loadPlayersFromCSV("spieler_zweiteliga.csv");
		excludedPlayers = IOHelper.loadPlayersFromCSV("exclusion_zweiteliga.csv");
		includedPlayers = IOHelper.loadPlayersFromCSV("inclusion_zweiteliga.csv");

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

}
