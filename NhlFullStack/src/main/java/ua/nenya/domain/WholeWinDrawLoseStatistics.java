package ua.nenya.domain;

public class WholeWinDrawLoseStatistics {
	private int winCount;
	private int drawCount;
	private int loseCount;
	
	public int getWinCount() {
		return winCount;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	public int getDrawCount() {
		return drawCount;
	}
	public void setDrawCount(int drawCount) {
		this.drawCount = drawCount;
	}
	public int getLoseCount() {
		return loseCount;
	}
	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}
	@Override
	public String toString() {
		return "WholeWinDrawLoseStatistics [winCount=" + winCount + ", drawCount=" + drawCount + ", loseCount="
				+ loseCount + "]";
	}	
	
}
