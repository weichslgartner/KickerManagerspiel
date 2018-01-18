package optimization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import IO.IOHelper;

import com.google.inject.Inject;

public class ThirdLeagueManagerSpielProblem implements ManagerSpielProblem {

	private List<Player> players;
	private List<Player> excludedPlayers;
	

	private List<Player> includedPlayers;
	
	
	@Inject
	public  ThirdLeagueManagerSpielProblem() {
		players = IOHelper.loadPlayersFromCSV("spieler_dritteliga.csv");
		excludedPlayers = IOHelper.loadPlayersFromCSV("exclusion_dritteliga.csv");
		includedPlayers = IOHelper.loadPlayersFromCSV("inclusion_dritteliga.csv");

	}
	
	@Override
	public List<Player> getExcludedPlayers() {
		return excludedPlayers;
	}
	
	@Override
	public List<Player> getIncludedPlayers() {
		return includedPlayers;
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return players;
	}
	
	public Collection<Player> getWinterPlayers() {
		return new ArrayList<Player>();
	}

}
