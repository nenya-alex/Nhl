package ua.nenya.domain;

public class Team {
	
	private String teamName;
	private int numberOfGames;
	
	private int numberOfOverTime;
	private int numberOfOverTimeWins;
	private int numberOfOverTimeLosses;
	private int points;
	private double pointPstg;
	private int wins;
	private int losses;
	private int draws;
	private double oddsOfWin;
	private double oddsOfDraw;
	private double oddsOfLose;
	
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
	public double getPointPstg() {
		return pointPstg;
	}
	public void setPointPstg(double pointPstg) {
		this.pointPstg = pointPstg;
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
	
	public double getOddsOfWin() {
		return oddsOfWin;
	}
	public void setOddsOfWin(double oddsOfWin) {
		this.oddsOfWin = oddsOfWin;
	}
	public double getOddsOfDraw() {
		return oddsOfDraw;
	}
	public void setOddsOfDraw(double oddsOfDraw) {
		this.oddsOfDraw = oddsOfDraw;
	}
	public double getOddsOfLose() {
		return oddsOfLose;
	}
	public void setOddsOfLose(double oddsOfLose) {
		this.oddsOfLose = oddsOfLose;
	}
	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", numberOfGames=" + numberOfGames + ", pointPstg=" + pointPstg
				+ ", wins=" + wins + ", losses=" + losses + ", draws=" + draws + ", oddsOfWin=" + oddsOfWin
				+ ", oddsOfDraw=" + oddsOfDraw + ", oddsOfLose=" + oddsOfLose + "]";
	}
		
}
