package optimization;
import java.util.Collection;
import java.util.List;

import IO.IOHelper;

import com.google.inject.Inject;

public class ThirdLeagueManagerSpielProblem implements ManagerSpielProblem {

	private List<Player> players;

	@Inject
	public  ThirdLeagueManagerSpielProblem() {
		players = IOHelper.loadPlayersFromCSV("spieler_dritteliga.csv");
	}
	
	@Override
	public Collection<Player> getPlayers() {
		return players;
	}

}
