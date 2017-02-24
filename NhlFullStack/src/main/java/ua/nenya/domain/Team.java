package ua.nenya.domain;

public class Team {
	
	private long id;
	private String teamName;
	private int numberOfGames;
	
	private int numberOfOverTime;
	private int numberOfOverTimeWins;
	private int numberOfOverTimeLosses;
	private int points;
	private float pointPstg;
	private int wins;
	private int losses;
	private int draws;
	
	
	public float getPointPstg() {
		return pointPstg;
	}
	public void setPointPstg(float pointPstg) {
		this.pointPstg = pointPstg;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getNumberOfGames() {
		return numberOfGames;
	}
	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}
	public int getNumberOfOverTime() {
		return numberOfOverTime;
	}
	public void setNumberOfOverTime(int numberOfOverTime) {
		this.numberOfOverTime = numberOfOverTime;
	}
	public int getNumberOfOverTimeWins() {
		return numberOfOverTimeWins;
	}
	public void setNumberOfOverTimeWins(int numberOfOverTimeWins) {
		this.numberOfOverTimeWins = numberOfOverTimeWins;
	}
	public int getNumberOfOverTimeLosses() {
		return numberOfOverTimeLosses;
	}
	public void setNumberOfOverTimeLosses(int numberOfOverTimeLosses) {
		this.numberOfOverTimeLosses = numberOfOverTimeLosses;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	@Override
	public String toString() {
		return "id = " + id + ", teamName:" + teamName + ", numberOfGames = " + numberOfGames + ", points = " + points
				+ ", pointPstg = " + pointPstg + ", wins=" + wins + ", losses=" + losses + ", draws=" + draws;
	}
	
}
