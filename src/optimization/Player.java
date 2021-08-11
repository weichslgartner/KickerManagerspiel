package optimization;
public class Player implements Comparable<Player> {
	private String name;
	private String club;
	private String position;
	private float value;
	private int points1516;
	private float rating1516;

	public Player(String name, String club, String position, float value,
			int points1516, float rating1516) {
		super();
		this.name = name;
		this.club = club;
		this.position = position;
		this.value = value;
		this.points1516 = points1516;
		this.rating1516 = rating1516;
	}

	public Player(String name, String club, String position, float value) {
		super();
		this.name = name;
		this.club = club;
		this.position = position;
		this.value = value;
		this.points1516 = 0;
		this.rating1516 = -1;
	}

	public String getName() {
		return name;
	}

	public String getClub() {
		return club;
	}

	public float getValue() {
		return value;
	}

	public int getPoints1516() {
		return points1516;
	}

	public float getRating1516() {
		return rating1516;
	}

	public void setPoints1516(int points1516) {
		this.points1516 = points1516;
	}

	public void setRating1516(float rating1516) {
		this.rating1516 = rating1516;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + "(" + this.club + " " + this.position + ")";
	}
	public String toCSVString() {
		// TODO Auto-generated method stub
		return this.name + ";" + this.club + ";" + this.position + ";"+ this.points1516 + ";\n";
	}
	
	public int compareTo(Player other) {
		return this.name.compareToIgnoreCase(other.name);
	}

	@Override
	public boolean equals(Object anObject) {
		if (!(anObject instanceof Player)) {
			return false;
		}
		Player otherPlayer = (Player)anObject;
		return this.compareTo(otherPlayer) == 0;
	}
	public String getPosition() {
		return position;
	}

}
