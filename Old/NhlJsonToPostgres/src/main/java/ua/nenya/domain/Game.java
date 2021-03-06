package ua.nenya.domain;

import java.time.LocalDate;

public class Game {
	private String homeTeam;
	private String guestTeam;
	private int homeGoals;
	private int guestGoals;
	private double oddsHomeWin;
	private double oddsDraw;
	private double oddsGuestWin;
	private LocalDate date;
	private boolean overTime;
	
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
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
		return "Game [homeTeam=" + homeTeam + ", guestTeam=" + guestTeam + ", homeGoals=" + homeGoals + ", guestGoals="
				+ guestGoals + ", oddsHomeWin=" + oddsHomeWin + ", oddsDraw=" + oddsDraw + ", oddsGuestWin="
				+ oddsGuestWin + ", date=" + date + ", overTime=" + overTime + "]";
	}
	
	

}
