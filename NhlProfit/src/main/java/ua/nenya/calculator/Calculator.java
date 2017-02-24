package ua.nenya.calculator;

import ua.nenya.domain.Game;
import ua.nenya.domain.Profit;
import ua.nenya.domain.BetResult;

public class Calculator {

	public void calculateProfitByCaseId(Game game, int caseId, double bet, Profit profit) {

		double myOddsHomeWin = game.getNewOddsOfWin().get(caseId);
		double myOddsDraw = game.getNewOddsOfDraw().get(caseId);
		double myOddsHomeLose = game.getNewOddsOfLose().get(caseId);
		
		int numberOfInfinity = calculateInfinityOdds(myOddsHomeWin, myOddsDraw, myOddsHomeLose);

		if(numberOfInfinity <= 1){
			if (myOddsHomeWin < game.getOddsHomeWin()) {
				if (!game.isOverTime() && game.getHomeGoals() > game.getGuestGoals()) {
					double odds = game.getOddsHomeWin();
					addTotalSumToProfit(odds, bet, profit, true);
					int winWins = profit.getWinWins();
					profit.setWinWins(winWins+1);
					double sumOfWin = profit.getWinSumOfWins();
					profit.setWinSumOfWins(sumOfWin + bet * (odds - 1));
					addBetResultOfWin(odds, bet, game, profit, caseId, true);

				} else {
					addTotalSumToProfit(0.0, bet, profit, false);
					int lostWins = profit.getLostWins();
					profit.setLostWins(lostWins+1);
					double sumOfWin = profit.getWinSumOfWins();
					profit.setWinSumOfWins(sumOfWin - bet);
					addBetResultOfWin(game.getOddsHomeWin(), bet, game, profit, caseId, false);
				}
			} else {
				if (myOddsHomeLose < game.getOddsHomeLose()) {
					if (!game.isOverTime() && game.getHomeGoals() < game.getGuestGoals()) {
						double odds = game.getOddsHomeLose();
						addTotalSumToProfit(odds, bet, profit, true);
						int winLost = profit.getWinLost();
						profit.setWinLost(winLost+1);
						double sumOfLost = profit.getWinSumOfLost();
						profit.setWinSumOfLost(sumOfLost+ bet * (odds - 1));
						addBetResultOfWin(odds, bet, game, profit, caseId, true);
					} else {
						addTotalSumToProfit(0.0, bet, profit, false);
						int lostLosts = profit.getLostLost();
						profit.setLostLost(lostLosts+1);
						double sumOfLost = profit.getWinSumOfLost();
						profit.setWinSumOfLost(sumOfLost - bet);
						addBetResultOfWin(game.getOddsHomeLose(), bet, game, profit, caseId, false);
					}
				} else {
					if (myOddsDraw < game.getOddsDraw()) {
						if (game.isOverTime()) {
							double odds = game.getOddsDraw();
							addTotalSumToProfit(odds, bet, profit, true);
							int winDraws = profit.getWinDraws();
							profit.setWinDraws(winDraws+1);
							double sumOfDraw = profit.getWinSumOfDraws();
							profit.setWinSumOfDraws(sumOfDraw + bet * (odds - 1));
							addBetResultOfWin(odds, bet, game, profit, caseId, true);
						} else {
							addTotalSumToProfit(0.0, bet, profit, false);
							int lostDraws = profit.getLostDraws();
							profit.setLostDraws(lostDraws+1);
							double sumOfDraw = profit.getWinSumOfDraws();
							profit.setWinSumOfDraws(sumOfDraw - bet);
							addBetResultOfWin(game.getOddsDraw(), bet, game, profit, caseId, false);
						}
					}
				}
			}
		}		
	}

	private int calculateInfinityOdds(double myOddsHomeWin, double myOddsDraw, double myOddsHomeLose) {
		int count = 0;
		if(!Double.isFinite(myOddsHomeWin)){
			count++;
		}
		if(!Double.isFinite(myOddsHomeLose)){
			count++;
		}
		if(!Double.isFinite(myOddsDraw)){
			count++;
		}
		return count;
	}

	private void addTotalSumToProfit(double odds, double bet, Profit profit, boolean isWin) {
		double totalSum = 0.0;
		if(isWin){
			totalSum = profit.getTotalSum() + bet * (odds - 1);
		}else{
			totalSum = profit.getTotalSum() - bet;
		}
		profit.setTotalSum(totalSum);
		profit.getTotalSums().add(totalSum);
	}

	private void addBetResultOfWin(double odds, double bet, Game game, Profit profit, int caseId, boolean isWin) {
		BetResult betResult = new BetResult();
		betResult.setX(game.getHomeTeam().getPoints());
		betResult.setY(game.getGuestTeam().getPoints());
		betResult.setGameId(game.getId());
		betResult.setPlayers(game.getHomeTeam().getTeamName()+" - "+game.getGuestTeam().getTeamName());
		
		if(game.isOverTime()){
			betResult.setScore(game.getHomeGoals()+":"+game.getGuestGoals()+" ET");
		}else{
			betResult.setScore(game.getHomeGoals()+":"+game.getGuestGoals());
		}
		
		betResult.setOdds(game.getOddsHomeWin()+"  "+game.getOddsDraw()+"  "+game.getOddsHomeLose());
		betResult.setNewOddsOfWin(game.getNewOddsOfWin().get(caseId));
		betResult.setNewOddsOfDraw(game.getNewOddsOfDraw().get(caseId));
		betResult.setNewOddsOfLose(game.getNewOddsOfLose().get(caseId));
		betResult.setTotalSumAfterBet(profit.getTotalSum());
		betResult.setWin(isWin);
		
		if(isWin){
			betResult.setWinSumWithoutBet(bet * (odds - 1));
		}else{
			betResult.setWinSumWithoutBet(-1.0* bet);
		}	
		
		profit.getBetResults().add(betResult);
	}

}
