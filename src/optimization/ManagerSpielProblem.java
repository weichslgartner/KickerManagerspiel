package optimization;
import java.util.Collection;


public interface ManagerSpielProblem {
	public Collection<Player> getPlayers();
	public Collection<Player> getExcludedPlayers();
	public Collection<Player> getIncludedPlayers();
	public Collection<Player> getWinterPlayers();
}
