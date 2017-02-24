package ua.nenya.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect
public class WholeGame {
	private String players;
	private String score;
	private String oddsHomeWin;
	private String oddsDraw;
	private String oddsGuestWin;
	private String date;
	@JsonIgnore
	private String season;	
	
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getPlayers() {
		return players;
	}
	public void setPlayers(String players) {
		this.players = players;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getOddsHomeWin() {
		return oddsHomeWin;
	}
	public void setOddsHomeWin(String oddsHomeWin) {
		this.oddsHomeWin = oddsHomeWin;
	}
	public String getOddsDraw() {
		return oddsDraw;
	}
	public void setOddsDraw(String oddsDraw) {
		this.oddsDraw = oddsDraw;
	}
	public String getOddsGuestWin() {
		return oddsGuestWin;
	}
	public void setOddsGuestWin(String oddsGuestWin) {
		this.oddsGuestWin = oddsGuestWin;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "WholeGame [players=" + players + ", score=" + score + ", oddsHomeWin=" + oddsHomeWin + ", oddsDraw="
				+ oddsDraw + ", oddsGuestWin=" + oddsGuestWin + ", date=" + date + ", season=" + season + "]";
	}
	
}
