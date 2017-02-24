package ua.nenya.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Profit {
	
	private List<Double> totalSums;
	private double totalSum;
	private int winWins;
	private int winLost;
	private int winDraws;
	
	private double winSumOfWins;
	private double winSumOfLost;
	private double winSumOfDraws;
	
	private int lostWins;
	private int lostLost;
	private int lostDraws;
	
	private List<BetResult> betResults;
	
	public Profit(){
		this.betResults = new ArrayList<>();
		this.totalSums = new ArrayList<>();
	}

	public double getWinSumOfWins() {
		return winSumOfWins;
	}

	public void setWinSumOfWins(double winSumOfWins) {
		this.winSumOfWins = winSumOfWins;
	}

	public double getWinSumOfLost() {
		return winSumOfLost;
	}

	public void setWinSumOfLost(double winSumOfLost) {
		this.winSumOfLost = winSumOfLost;
	}

	public double getWinSumOfDraws() {
		return winSumOfDraws;
	}

	public void setWinSumOfDraws(double winSumOfDraws) {
		this.winSumOfDraws = winSumOfDraws;
	}

	public List<Double> getTotalSums() {
		return totalSums;
	}

	public void setTotalSums(List<Double> totalSums) {
		this.totalSums = totalSums;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	public int getWinWins() {
		return winWins;
	}

	public void setWinWins(int winWins) {
		this.winWins = winWins;
	}

	public int getWinLost() {
		return winLost;
	}

	public void setWinLost(int winLost) {
		this.winLost = winLost;
	}

	public int getWinDraws() {
		return winDraws;
	}

	public void setWinDraws(int winDraws) {
		this.winDraws = winDraws;
	}

	public int getLostWins() {
		return lostWins;
	}

	public void setLostWins(int lostWins) {
		this.lostWins = lostWins;
	}

	public int getLostLost() {
		return lostLost;
	}

	public void setLostLost(int lostLost) {
		this.lostLost = lostLost;
	}

	public int getLostDraws() {
		return lostDraws;
	}

	public void setLostDraws(int lostDraws) {
		this.lostDraws = lostDraws;
	}

	public List<BetResult> getBetResults() {
		return betResults;
	}

	public void setBetResults(List<BetResult> betResults) {
		this.betResults = betResults;
	}

}
