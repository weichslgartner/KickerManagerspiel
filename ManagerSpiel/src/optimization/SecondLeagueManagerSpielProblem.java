package optimization;
import java.util.Collection;
import java.util.List;

import IO.IOHelper;

import com.google.inject.Inject;

public class SecondLeagueManagerSpielProblem implements ManagerSpielProblem {

	private List<Player> players;

	@Inject
	public  SecondLeagueManagerSpielProblem() {
		players = IOHelper.loadPlayersFromCSV("spieler_zweiteliga.csv");
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return players;
	}

}
