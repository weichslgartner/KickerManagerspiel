package optimization;
import org.opt4j.core.common.logger.OutputModule;
import org.opt4j.core.config.annotations.Info;
import org.opt4j.core.config.annotations.Required;



public class ManagerSpielLoggerModule extends OutputModule {
	@Info("Log per evaluation activated.")
	protected boolean loggingPerEvaluation = false;

	@Info("Log per iteration activated.")
	protected boolean loggingPerIteration = true;

	@Info("Number of evaluations after which the archive is logged.")
	@Required(property = "loggingPerEvaluation", elements = { "TRUE" })
	protected int evaluationStep = 100;

	@Info("Number of iterations after which the archive is logged.")
	@Required(property = "loggingPerIteration", elements = { "TRUE" })
	protected int iterationStep = 1;
	
	@Override
	protected void config() {
		bind(ManagerSpielLogger.class).in(SINGLETON);
		addIndividualStateListener(ManagerSpielLogger.class);
		addOptimizerIterationListener(ManagerSpielLogger.class);
		addOptimizerStateListener(ManagerSpielLogger.class);	
		
		int evaluationStep = this.evaluationStep;
		int iterationStep = this.iterationStep;
		
		if (!loggingPerEvaluation) {
			evaluationStep = -1;
		}
		if (!loggingPerIteration) {
			iterationStep = -1;
		}
		bindConstant("evaluationStep", ManagerSpielLogger.class).to(evaluationStep);
		bindConstant("iterationStep", ManagerSpielLogger.class).to(iterationStep);
	}

}
