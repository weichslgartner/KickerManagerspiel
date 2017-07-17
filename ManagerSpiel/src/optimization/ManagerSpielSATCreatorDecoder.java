package optimization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opt4j.core.Genotype;
import org.opt4j.core.common.random.Rand;
import org.opt4j.core.start.Constant;
import org.opt4j.satdecoding.AbstractSATDecoder;
import org.opt4j.satdecoding.Constraint;
import org.opt4j.satdecoding.Constraint.Operator;
import org.opt4j.satdecoding.Literal;
import org.opt4j.satdecoding.Model;
import org.opt4j.satdecoding.SATManager;

import com.google.inject.Inject;

/**
 * Use SAT decoding to solve the kicker ManagerSpiel. (modified knapsack problem)
 * 
 * @author weichslgartner
 * 
 */
public class ManagerSpielSATCreatorDecoder extends
		AbstractSATDecoder<Genotype, PlayerSelection> {

	private final ManagerSpielProblem problem;
	private double budget;

	/**
	 * Creates a new {@link ManagerSpielSATCreatorDecoder}.
	 * 
	 * @param manager
	 *            the sat manager
	 * @param problem
	 *            the problem instance
	 */
	@Inject
	public ManagerSpielSATCreatorDecoder(SATManager manager,
			ManagerSpielProblem problem, Rand random,@Constant(value = "teambudget") double budget) {
		super(manager, random);
		this.problem = problem;
		this.budget =budget;
	}

	@Override
	public Set<Constraint> createConstraints() {
		Set<Constraint> constraints = new HashSet<Constraint>();
		Constraint torConstraint = new Constraint(Operator.EQ, 3);
		Constraint abwConstraint = new Constraint(Operator.EQ, 6);
		Constraint mitConstraint = new Constraint(Operator.EQ, 8);
		Constraint stuConstraint = new Constraint(Operator.EQ, 5);
//		Constraint maxValueConstraint = new Constraint(Operator.LE,(int)(budget*100));
		Map<String, Constraint> maxPlayerPerTeamConstraints = new HashMap<String, Constraint>();

		for (Player player : problem.getPlayers()) {

			switch (player.getPosition()) {
			case Positions.TOR:
				torConstraint.add(1, new Literal(player, true));
				break;
			case Positions.ABW:
				abwConstraint.add(1, new Literal(player, true));
				break;
			case Positions.MIT:
				mitConstraint.add(1, new Literal(player, true));
				break;
			case Positions.STU:
				stuConstraint.add(1, new Literal(player, true));
				break;
			default:
				System.err.println("Undefined Position");
			}
//			maxValueConstraint.add((int) (player.getValue() * 100), new Literal(
//					player, true));

			if (!maxPlayerPerTeamConstraints.containsKey(player.getClub())) {
				Constraint clubConstraint = new Constraint(Operator.LE, 4);

				maxPlayerPerTeamConstraints.put(player.getClub(),
						clubConstraint);
			}
			maxPlayerPerTeamConstraints.get(player.getClub()).add(1,
					new Literal(player, true));

		}
		constraints.add(torConstraint);
		constraints.add(abwConstraint);
		constraints.add(mitConstraint);
		constraints.add(stuConstraint);
		//constraints.add(maxValueConstraint);
		for(String key : maxPlayerPerTeamConstraints.keySet()){
			constraints.add(maxPlayerPerTeamConstraints.get(key));
		}
		
		return constraints;
	}

	@Override
	public PlayerSelection convertModel(Model model) {
		PlayerSelection playerSelection = new PlayerSelection();
		for (Player player : problem.getPlayers()) {
			if (model.get(player)) {
				playerSelection.add(player);
			}
		}
		return playerSelection;
	}

}
