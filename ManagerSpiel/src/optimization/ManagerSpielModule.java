package optimization;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

public class ManagerSpielModule extends ProblemModule {

	@Constant(value = "3-4-3")
	protected boolean tactics343 = true;

	
	@Constant(value = "3-5-2")
	protected boolean tactics352 = true;
	
	@Constant(value = "4-3-3")
	protected boolean tactics433 = true;
	
	@Constant(value = "4-4-2")
	protected boolean tactics442 = true;
	
	@Constant(value = "4-5-1")
	protected boolean tactics451 = true;
	
	@Override
	protected void config() {
		bind(ManagerSpielProblem.class).to(SecondLeagueManagerSpielProblem.class).in(SINGLETON);
		bindProblem(ManagerSpielSATCreatorDecoder.class, ManagerSpielSATCreatorDecoder.class, ManagerSpielEvaluator.class);
	}

	public boolean isTactics343() {
		return tactics343;
	}

	public void setTactics343(boolean tactics343) {
		this.tactics343 = tactics343;
	}

	public boolean isTactics352() {
		return tactics352;
	}

	public void setTactics352(boolean tactics352) {
		this.tactics352 = tactics352;
	}

	public boolean isTactics433() {
		return tactics433;
	}

	public void setTactics433(boolean tactics433) {
		this.tactics433 = tactics433;
	}

	public boolean isTactics442() {
		return tactics442;
	}

	public void setTactics442(boolean tactics442) {
		this.tactics442 = tactics442;
	}

	public boolean isTactics451() {
		return tactics451;
	}

	public void setTactics451(boolean tactics451) {
		this.tactics451 = tactics451;
	}

}
