package ua.nenya.domain;

public class Dot {

	private float x;
	private float y;
	private String season;
	private int gameId;
	private String result;
		
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
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
}
