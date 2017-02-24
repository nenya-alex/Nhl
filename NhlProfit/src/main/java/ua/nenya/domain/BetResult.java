package ua.nenya.domain;

public class BetResult {
	
	private int x;
	private int y;
	private long gameId; 
	private String players;
	private String score;
	private String odds;
	private double newOddsOfWin;
	private double newOddsOfDraw;
	private double newOddsOfLose;
	private boolean isWin;
	private double winSumWithoutBet;
	private double totalSumAfterBet;	
	
	public double getWinSumWithoutBet() {
		return winSumWithoutBet;
	}
	public void setWinSumWithoutBet(double winSumWithoutBet) {
		this.winSumWithoutBet = winSumWithoutBet;
	}
	public double getTotalSumAfterBet() {
		return totalSumAfterBet;
	}
	public void setTotalSumAfterBet(double totalSumAfterBet) {
		this.totalSumAfterBet = totalSumAfterBet;
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
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
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
	public String getOdds() {
		return odds;
	}
	public void setOdds(String odds) {
		this.odds = odds;
	}
	public double getNewOddsOfWin() {
		return newOddsOfWin;
	}
	public void setNewOddsOfWin(double newOddsOfWin) {
		this.newOddsOfWin = newOddsOfWin;
	}
	public double getNewOddsOfDraw() {
		return newOddsOfDraw;
	}
	public void setNewOddsOfDraw(double newOddsOfDraw) {
		this.newOddsOfDraw = newOddsOfDraw;
	}
	public double getNewOddsOfLose() {
		return newOddsOfLose;
	}
	public void setNewOddsOfLose(double newOddsOfLose) {
		this.newOddsOfLose = newOddsOfLose;
	}
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	
}
