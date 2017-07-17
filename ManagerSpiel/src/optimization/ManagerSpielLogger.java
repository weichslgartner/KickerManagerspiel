package optimization;
import java.util.Collection;
import java.util.Map.Entry;

import org.opt4j.core.Individual;
import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.Value;
import org.opt4j.core.common.logger.AbstractLogger;
import org.opt4j.core.common.logger.Logger;
import org.opt4j.core.optimizer.Archive;
import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

public class ManagerSpielLogger extends AbstractLogger implements Logger {
	private Archive archive;

	@Inject
	public ManagerSpielLogger(
			Archive archive,
			@Constant(value = "evaluationStep", namespace = ManagerSpielLogger.class) int evaluationStep,
			@Constant(value = "iterationStep", namespace = ManagerSpielLogger.class) int iterationStep) {
		super(iterationStep, evaluationStep);
		this.archive = archive;
	}

	@Override
	public void logEvent(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logHeader(Collection<Objective> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void optimizationStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void optimizationStopped() {
		double bestteamPoints = 0;
		String bestObjectveName = "";
		String bestTeam ="";
		System.out.println("Optimized Team:");
		for (Individual individual : archive) {
			String curTeam ="";
			PlayerSelection phenotype = (PlayerSelection) individual
					.getPhenotype();
			
			Objectives objectives = individual.getObjectives();

			curTeam = phenotype.getTeamSortedByPosAndPoints();
			curTeam  += "\nOverall value: " + phenotype.getTeamValue();

			double bestIndividualObjectiveValue =0;
			String bestIndividualObjectiveName ="";
			
			//find tactics with most points
			//skip overall points objective
			for (Entry<Objective, Value<?>> objective : objectives) {
				if (objective.getKey().getName().contains("overall")){
					continue;
				}
				double curValue = objective.getValue().getDouble();
				if (curValue >bestIndividualObjectiveValue){
					bestIndividualObjectiveValue = curValue;
					bestIndividualObjectiveName = objective.getKey().getName();
				}
			}
			
			if (bestIndividualObjectiveValue > bestteamPoints) {
				bestTeam = curTeam;
				bestteamPoints = bestIndividualObjectiveValue;
				bestObjectveName = bestIndividualObjectiveName;
			}
		}

		System.out.println("Tactics: " + bestObjectveName.substring(0, 3) + " Points: " + bestteamPoints + " Team:\n"+bestTeam);
	}

}
