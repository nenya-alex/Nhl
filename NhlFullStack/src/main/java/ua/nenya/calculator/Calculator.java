package ua.nenya.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.nenya.domain.BetResult;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Profit;
import ua.nenya.domain.Team;
import ua.nenya.domain.WholeWinDrawLoseStatistics;

public class Calculator {

	public Team calculateTeamStatistic(List<Game> games, String teamName, long id) {

		int numberOfOverTime = 0;
		int numberOfOverTimeWins = 0;
		int numberOfOverTimeLosses = 0;
		int points = 0;
		int wins = 0;
		int losses = 0;
		int draws = 0;
		Team team = new Team();

		List<Game> teamGames = getAllGamesByTeamName(teamName, id, games);

		team.setTeamName(teamName);

		int numberOfGames = teamGames.size();
		team.setNumberOfGames(numberOfGames);

		for (int i = 0; i < teamGames.size(); i++) {
			Game game = teamGames.get(i);

			if (game.isOverTime()) {
				draws++;
				numberOfOverTime++;
				if (game.getHomeTeam().getTeamName().equals(teamName)) {
					if (game.getHomeGoals() > game.getGuestGoals()) {
						points = points + 2;
						numberOfOverTimeWins++;
					} else {
						points++;
						numberOfOverTimeLosses++;
					}
				} else {
					if (game.getHomeGoals() < game.getGuestGoals()) {
						points = points + 2;
						numberOfOverTimeWins++;
					} else {
						points++;
						numberOfOverTimeLosses++;
					}
				}
			} else {
				if (game.getHomeTeam().getTeamName().equals(teamName)) {
					if (game.getHomeGoals() > game.getGuestGoals()) {
						points = points + 2;
						wins++;
					} else {
						losses++;
					}
				} else {
					if (game.getHomeGoals() < game.getGuestGoals()) {
						points = points + 2;
						wins++;
					} else {
						losses++;
					}
				}
			}
		}

		team.setDraws(draws);
		team.setLosses(losses);
		team.setWins(wins);

		team.setNumberOfOverTime(numberOfOverTime);
		team.setNumberOfOverTimeWins(numberOfOverTimeWins);
		team.setNumberOfOverTimeLosses(numberOfOverTimeLosses);

		team.setPoints(points);
		team.setPointPstg((float) points / ((float) numberOfGames * 2));

		return team;
	}

	public List<Dot> getEmptyDots(int beginOfInterval, int endOfInterval, int numberOfIntervals) {
		List<Dot> dots = new ArrayList<>();
		for (int i = beginOfInterval; i <= endOfInterval; i++) {
			for (int j = beginOfInterval; j <= endOfInterval; j++) {
				Dot dot = new Dot();
				dot.setX(i);
				dot.setY(j);
				dots.add(dot);
			}
		}
		return dots;
	}

	public void fillDotsByGames(List<Dot> dots, Game game, String result) {

		int homeTeamRating = game.getHomeTeam().getPoints();
		int guestTeamRating = game.getGuestTeam().getPoints();

		for (Dot dot : dots) {
			if (homeTeamRating == dot.getX() && guestTeamRating == dot.getY()) {
				Map<String, List<Game>> resultMap = dot.getResult();
				for (Map.Entry<String, List<Game>> pair : resultMap.entrySet()) {
					if (pair.getKey().equals(result)) {
						List<Game> games = pair.getValue();
						games.add(game);
					}
				}
				break;
			}
		}
	}

	public boolean isHomeTeamWinner(Game game) {
		return game.getHomeGoals() > game.getGuestGoals();
	}

	private List<Game> getAllGamesByTeamName(String team, long id, List<Game> games) {

		List<Game> teamGames = new ArrayList<>();

		for (Game game : games) {
			String homeTeamName = game.getHomeTeam().getTeamName();
			String guestTeamName = game.getGuestTeam().getTeamName();
			long gameId = game.getId();

			if ((team.equals(homeTeamName) || team.equals(guestTeamName)) && gameId < id) {
				teamGames.add(game);
			}
		}
		return teamGames;
	}
	
	public void calculateNewOdds(List<Game> games, List<Dot> dots, WholeWinDrawLoseStatistics wholeWinDrawLoseStatistics) {
		for (Game game: games) {
			calculateNewOddsForGame(game, dots, wholeWinDrawLoseStatistics);					
		}
	}
	
	private void calculateNewOddsForGame(Game game, List<Dot> dots, WholeWinDrawLoseStatistics wholeWinDrawLoseStatistics) {
		
		int homePoints = game.getHomeTeam().getPoints();
		int guestPoints = game.getGuestTeam().getPoints();

		List<Double> newOddsOfWin = new ArrayList<>();
		List<Double> newOddsOfDraw = new ArrayList<>();
		List<Double> newOddsOfLose = new ArrayList<>();

		for (Dot dot : dots) {
			if (homePoints == dot.getX() && guestPoints == dot.getY()) {
				int allDrawsInDot = dot.getDrawLoseET() + dot.getDrawWinET() + dot.getDrawLosePen()
						+ dot.getDrawWinPen();
				int allGamesInDot = dot.getWins() + allDrawsInDot + dot.getLost();

				//coefficients by simple probability
				newOddsOfWin.add((double) allGamesInDot / (double) dot.getWins());
				newOddsOfDraw.add((double) allGamesInDot / (double) allDrawsInDot);
				newOddsOfLose.add((double) allGamesInDot / (double) dot.getLost());
				
				//coefficients by Bayes probability
				int numerator = dot.getWins()*wholeWinDrawLoseStatistics.getWinCount() 
						+ allDrawsInDot*wholeWinDrawLoseStatistics.getDrawCount()
						+ dot.getLost()*wholeWinDrawLoseStatistics.getLoseCount();
				
				newOddsOfWin.add((double) numerator / (double) dot.getWins()*wholeWinDrawLoseStatistics.getWinCount());
				newOddsOfDraw.add((double) numerator / (double) allDrawsInDot*wholeWinDrawLoseStatistics.getDrawCount());
				newOddsOfLose.add((double) numerator / (double) dot.getLost()*wholeWinDrawLoseStatistics.getLoseCount());
				
				game.setNewOddsOfWin(newOddsOfWin);
				game.setNewOddsOfDraw(newOddsOfDraw);
				game.setNewOddsOfLose(newOddsOfLose);
				break;
			}
		}
	}
	
	public Profit calculateProfitOfSeason(List<Game> games, int caseId, double bet, double initialSum) {
		Profit profit = new Profit();
		profit.setSeason(games.get(0).getSeason());
		profit.setTotalSum(initialSum);
		for (int i = 200; i < games.size(); i++) {
			calculateProfitOfGame(games.get(i), caseId, bet, initialSum, profit);					
		}
		return profit;
	}
	
	private void calculateProfitOfGame(Game game, int caseId, double bet, double initialSum, Profit profit) {
		
		double myOddsHomeWin = game.getNewOddsOfWin().get(caseId);
		double myOddsDraw = game.getNewOddsOfDraw().get(caseId);
		double myOddsHomeLose = game.getNewOddsOfLose().get(caseId);
		//TODO set game odds here 
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

	public WholeWinDrawLoseStatistics calculateWholeWinDrawLose(List<Dot> dots) {
		WholeWinDrawLoseStatistics result = new WholeWinDrawLoseStatistics();
		int wins = 0;
		int draws = 0;
		int loses = 0;
		
		for(Dot dot: dots){
			wins = wins + dot.getWins();
			loses = loses + dot.getLost();
			draws = draws + dot.getDrawLoseET()+dot.getDrawWinET() + dot.getDrawLosePen() + dot.getDrawWinPen();
		}
		result.setWinCount(wins);
		result.setLoseCount(loses);
		result.setDrawCount(draws);
		return result;
	}

}
