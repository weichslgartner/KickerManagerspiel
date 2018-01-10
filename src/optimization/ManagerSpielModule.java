package optimization;
import org.opt4j.core.config.annotations.Info;
import org.opt4j.core.config.annotations.Order;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

public class ManagerSpielModule extends ProblemModule {

//	@Required(property = "league", elements = { "SECONDLEAGUE", "ThirL"})
	@Order(10)
    @Info("Select the League")
	protected League league = League.SECONDLEAGUE;
	@Constant(value = "3-4-3")
	protected boolean tactics343 = true;
	
	@Constant(value = "4-5-3")
	protected boolean tactics453 = true;
	
	@Constant(value = "3-5-2")
	protected boolean tactics352 = true;
	
	@Constant(value = "4-3-3")
	protected boolean tactics433 = true;
	
	@Constant(value = "4-4-2")
	protected boolean tactics442 = true;
	
	@Constant(value = "4-5-1")
	protected boolean tactics451 = true;
	
	@Constant(value = "classic")
	protected boolean classic = true;
	
	@Constant(value = "winter")
	protected boolean winter = true;
	
	@Constant(value = "budget") 
	protected double budget = 42;
	
	
	@Override
	protected void config() {
		switch (league) {
		case FIRSTLEAGUE:
			setBudget(42);
			bind(ManagerSpielProblem.class).to(FirstLeagueManagerSpielProblem.class).in(SINGLETON);

			break;
		case FIRSTLEAGUEWINTER:
			setBudget(42);
			bind(ManagerSpielProblem.class).to(FirstLeagueManagerSpielProblemWinter.class).in(SINGLETON);
	
			break;
		
		case SECONDLEAGUE:
			setBudget(10);
			bind(ManagerSpielProblem.class).to(SecondLeagueManagerSpielProblem.class).in(SINGLETON);

			break;
			
		case THIRDLEAGUE:
			setBudget(6);
			bind(ManagerSpielProblem.class).to(ThirdLeagueManagerSpielProblem.class).in(SINGLETON);

			break;
		default:
			break;
		}
		bindConstant("budget", ManagerSpielEvaluator.class).to(budget);
		bindConstant("budget", ManagerSpielSATCreatorDecoder.class).to(budget);
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

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
	
	public double getBudget() {
		return budget;
	}

	public void setBudget(double teambudget) {
		this.budget = teambudget;
	}

	public boolean isTactics453() {
		return tactics453;
	}

	public void setTactics453(boolean tactics453) {
		this.tactics453 = tactics453;
	}

	public boolean isClassic() {
		return classic;
	}

	public void setClassic(boolean classic) {
		this.classic = classic;
	}

	public boolean isWinter() {
		return winter;
	}

	public void setWinter(boolean winter) {
		this.winter = winter;
	}

}
