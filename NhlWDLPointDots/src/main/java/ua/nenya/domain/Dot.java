package ua.nenya.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Dot {

	private int x;
	private int y;
	private String season;
	private int gameId;
	private Map<String, Integer> result;
	
	public Dot() {
		result = new HashMap<>();
		result.put("homeWinner", 0);
		result.put("draw", 0);
		result.put("guestWinner", 0);
	}
	public Map<String, Integer> getResult() {
		return result;
	}
	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	@Override
	public String toString() {
		return "Dot [x=" + x + ", y=" + y + "]";
	}
	
}
