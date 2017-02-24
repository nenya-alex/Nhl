package ua.nenya.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Profit;
import ua.nenya.domain.Team;
import ua.nenya.domain.WholeGame;
import ua.nenya.domain.WholeWinDrawLoseStatistics;
import ua.nenya.util.Contstants;
import ua.nenya.util.JsonConverter;
import ua.nenya.util.ParserFromWholeGameToGame;

public class Controller {
	
	public Calculator calculator = new Calculator();

	public List<List<WholeGame>> initWholeGames(List<String> filesForStatistics) {
		List<List<WholeGame>> wholeGames = new ArrayList<>();
		for (String fileName : filesForStatistics) {
			List<WholeGame> games = new JsonConverter<WholeGame>().initWholeGamesFromJson(fileName);
			games.forEach(game -> game.setSeason(fileName.substring(fileName.length() - 7, fileName.length() - 5)));
			wholeGames.add(games);
		}
		return wholeGames;
	}

	public void fillGamesByStatistics(List<List<Game>> games) {
		for (List<Game> itGames : games) {
			for (int id = 0; id < itGames.size(); id++) {
				Game game = itGames.get(id);

				Team homeTeam = calculator.calculateTeamStatistic(itGames, game.getHomeTeam().getTeamName(), id);
				Team guestTeam = calculator.calculateTeamStatistic(itGames, game.getGuestTeam().getTeamName(), id);

				game.setHomeTeam(homeTeam);
				game.setGuestTeam(guestTeam);
			}
		}
		// String fileNameForAllGamesSeason = "allGames"+fileName.substring(22,
				// 24)+".json";
				// new JsonConverter<Game>().convertToJSON(games, new
				// File(fileNameForAllGamesSeason));
	}

	public List<Dot> getAllDots(int beginOfInterval, int endOfInterval, int numberOfIntervals,
			List<List<Game>> gamesWithStatistics) {
		List<Dot> dots = calculator.getEmptyDots(beginOfInterval, endOfInterval, numberOfIntervals);
		for (List<Game> itGames : gamesWithStatistics) {
			for (int id = 0; id < itGames.size(); id++) {
				Game game = itGames.get(id);
				if (!game.isOverTime()) {
					if (calculator.isHomeTeamWinner(game)) {
						calculator.fillDotsByGames(dots, game, Contstants.HOME_WINER);
					} else {
						calculator.fillDotsByGames(dots, game, Contstants.HOME_LOSER);
					}
				} else {
					if (game.getPenalty()) {
						if (game.getHomeGoals() > game.getGuestGoals()) {
							calculator.fillDotsByGames(dots, game, Contstants.DRAW_WIN_PEN);
						} else {
							calculator.fillDotsByGames(dots, game, Contstants.DRAW_LOSE_PEN);
						}
					} else {
						if (game.getHomeGoals() > game.getGuestGoals()) {
							calculator.fillDotsByGames(dots, game, Contstants.DRAW_WIN_ET);
						} else {
							calculator.fillDotsByGames(dots, game, Contstants.DRAW_LOSE_ET);
						}
					}
				}
			}
		}

		return dots;
	}

	public void fillDotsByWinsLosesDraws(List<Dot> dots) {
		for (Dot dot : dots) {
			dot.setWins(dot.getResult().get(Contstants.HOME_WINER).size());
			dot.setLost(dot.getResult().get(Contstants.HOME_LOSER).size());
			dot.setDrawWinET(dot.getResult().get(Contstants.DRAW_WIN_ET).size());
			dot.setDrawLoseET(dot.getResult().get(Contstants.DRAW_LOSE_ET).size());
			dot.setDrawWinPen(dot.getResult().get(Contstants.DRAW_WIN_PEN).size());
			dot.setDrawLosePen(dot.getResult().get(Contstants.DRAW_LOSE_PEN).size());
		}
	}
	
	public void setNewOddsInGames(List<List<Game>> games, List<Dot> dots, WholeWinDrawLoseStatistics wholeWinDrawLoseStatistics) {
		for (List<Game> itGames : games) {
			calculator.calculateNewOdds(itGames, dots, wholeWinDrawLoseStatistics);
		}
	}
	
	public List<Profit> getProfitByCaseId(List<List<Game>> games, int caseId, double bet, double initialSum) {
		List<Profit> profits = new ArrayList<>();
		for (List<Game> itGames: games) {
			Profit profit = calculator.calculateProfitOfSeason(itGames, caseId, bet, initialSum);
			profits.add(profit);
		}
		return profits;		
	}
}
