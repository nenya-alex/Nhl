package ua.nenya.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;

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

	public List<Dot> getDots(int beginOfInterval, int endOfInterval, int numberOfIntervals) {
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
		if (game.getHomeGoals() > game.getGuestGoals()) {
			return true;
		} else {
			return false;
		}
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

}
