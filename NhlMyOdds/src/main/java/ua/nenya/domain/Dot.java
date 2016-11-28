package ua.nenya.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.nenya.util.Contstants;

@JsonAutoDetect
public class Dot {

	private int x;
	private int y;
	@JsonIgnore
	private Map<String, List<Game>> result;
	private int wins;
	private int lost;
	private int drawWinET;
	private int drawLoseET;
	private int drawWinPen;
	private int drawLosePen;
	
	public Dot() {
		result = new HashMap<>();
		result.put(Contstants.HOME_WINER, new ArrayList<Game>());
		result.put(Contstants.DRAW_WIN_ET, new ArrayList<Game>());
		result.put(Contstants.DRAW_LOSE_ET, new ArrayList<Game>());
		result.put(Contstants.DRAW_WIN_PEN, new ArrayList<Game>());
		result.put(Contstants.DRAW_LOSE_PEN, new ArrayList<Game>());
		result.put(Contstants.HOME_LOSER, new ArrayList<Game>());
	}
		
	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getDrawWinET() {
		return drawWinET;
	}

	public void setDrawWinET(int drawWinET) {
		this.drawWinET = drawWinET;
	}

	public int getDrawLoseET() {
		return drawLoseET;
	}

	public void setDrawLoseET(int drawLoseET) {
		this.drawLoseET = drawLoseET;
	}

	public int getDrawWinPen() {
		return drawWinPen;
	}

	public void setDrawWinPen(int drawWinPen) {
		this.drawWinPen = drawWinPen;
	}

	public int getDrawLosePen() {
		return drawLosePen;
	}

	public void setDrawLosePen(int drawLosePen) {
		this.drawLosePen = drawLosePen;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public Map<String, List<Game>> getResult() {
		return result;
	}

	public void setResult(Map<String, List<Game>> result) {
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
	
	@Override
	public String toString() {
		return "Dot [x=" + x + ", y=" + y + "]";
	}
	
}
