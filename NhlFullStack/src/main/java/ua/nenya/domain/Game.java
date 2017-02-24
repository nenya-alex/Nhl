package ua.nenya.domain;

import java.util.List;

public class Game {
	private long id;
	private String season;
	private Team homeTeam;
	private Team guestTeam;
	private int homeGoals;
	private int guestGoals;
	
	private double oddsHomeWin;
	private double oddsDraw;
	private double oddsHomeLose;
	
	private List<Double> newOddsOfWin;
	private List<Double> newOddsOfDraw;
	private List<Double> newOddsOfLose;
	
	private String date;
	private boolean overTime;
	private boolean penalty;	
		
	public Game() {
		this.homeTeam = new Team();
		this.guestTeam = new Team();
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(Team guestTeam) {
		this.guestTeam = guestTeam;
	}
	public boolean getPenalty() {
		return penalty;
	}
	public void setPenalty(boolean penalty) {
		this.penalty = penalty;
	}
	public List<Double> getNewOddsOfWin() {
		return newOddsOfWin;
	}
	public void setNewOddsOfWin(List<Double> newOddsOfWin) {
		this.newOddsOfWin = newOddsOfWin;
	}
	public List<Double> getNewOddsOfDraw() {
		return newOddsOfDraw;
	}
	public void setNewOddsOfDraw(List<Double> newOddsOfDraw) {
		this.newOddsOfDraw = newOddsOfDraw;
	}
	public List<Double> getNewOddsOfLose() {
		return newOddsOfLose;
	}
	public void setNewOddsOfLose(List<Double> newOddsOfLose) {
		this.newOddsOfLose = newOddsOfLose;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getHomeGoals() {
		return homeGoals;
	}
	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}
	public int getGuestGoals() {
		return guestGoals;
	}
	public void setGuestGoals(int guestGoals) {
		this.guestGoals = guestGoals;
	}
	
	public double getOddsHomeWin() {
		return oddsHomeWin;
	}
	public void setOddsHomeWin(double oddsHomeWin) {
		this.oddsHomeWin = oddsHomeWin;
	}
	public double getOddsDraw() {
		return oddsDraw;
	}
	public void setOddsDraw(double oddsDraw) {
		this.oddsDraw = oddsDraw;
	}
	public double getOddsHomeLose() {
		return oddsHomeLose;
	}
	public void setOddsHomeLose(double oddsGuestWin) {
		this.oddsHomeLose = oddsGuestWin;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isOverTime() {
		return overTime;
	}
	public void setOverTime(boolean overTime) {
		this.overTime = overTime;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", season=" + season + ", homeTeam=" + homeTeam + ", guestTeam=" + guestTeam
				+ ", homeGoals=" + homeGoals + ", guestGoals=" + guestGoals + ", oddsHomeWin=" + oddsHomeWin
				+ ", oddsDraw=" + oddsDraw + ", oddsHomeLose=" + oddsHomeLose + ", newOddsOfWin=" + newOddsOfWin
				+ ", newOddsOfDraw=" + newOddsOfDraw + ", newOddsOfLose=" + newOddsOfLose + ", date=" + date
				+ ", overTime=" + overTime + ", penalty=" + penalty + "]";
	}
	
}
