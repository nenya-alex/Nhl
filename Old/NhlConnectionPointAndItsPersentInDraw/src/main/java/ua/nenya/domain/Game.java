package ua.nenya.domain;

import java.time.LocalDate;
import java.util.List;

public class Game {
	private long id;
	private String homeTeamName;
	private String guestTeamName;
	private int homeGoals;
	private int guestGoals;
	
	private double oddsHomeWin;
	private double oddsDraw;
	private double oddsGuestWin;
	
	private List<Double> newOddsOfWin;
	private List<Double> newOddsOfDraw;
	private List<Double> newOddsOfLose;
	
	private LocalDate date;
	private boolean overTime;	
	
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
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeam) {
		this.homeTeamName = homeTeam;
	}
	public String getGuestTeamName() {
		return guestTeamName;
	}
	public void setGuestTeamName(String guestTeam) {
		this.guestTeamName = guestTeam;
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
	public double getOddsGuestWin() {
		return oddsGuestWin;
	}
	public void setOddsGuestWin(double oddsGuestWin) {
		this.oddsGuestWin = oddsGuestWin;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public boolean isOverTime() {
		return overTime;
	}
	public void setOverTime(boolean overTime) {
		this.overTime = overTime;
	}
	@Override
	public String toString() {
		return "Game number: " + id + ", date: " + date+ ", overTime: " + overTime
				+ "\n " + homeTeamName + "	-	" + guestTeamName 
				+ "\n		" + homeGoals	+ "	-	" + guestGoals 
				+ "\n Winner		1	:	X	:	2"
				+ "\n betOdds:	" + oddsHomeWin + "	:	" + oddsDraw + "	:	" + oddsGuestWin   
				+ "\n myOdds: " + newOddsOfWin + " : " + newOddsOfDraw + " : "+newOddsOfLose;
	}
}
