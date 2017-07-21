package optimization;
import java.util.Collection;
import java.util.List;

import IO.IOHelper;

import com.google.inject.Inject;

public class FirstLeagueManagerSpielProblem implements ManagerSpielProblem {

	private List<Player> players;

	@Inject
	public  FirstLeagueManagerSpielProblem() {
		players = IOHelper.loadPlayersFromCSV("spieler_ersteliga.csv");
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return players;
	}

}
