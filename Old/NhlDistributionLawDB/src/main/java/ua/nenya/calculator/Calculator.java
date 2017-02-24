package ua.nenya.calculator;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.domain.Cell;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;
import ua.nenya.domain.Unit;

public class Calculator {

	public Team calculateTeamStat(List<Game> games, String teamName) {
		int numberOfOverTime = 0;
		int numberOfOverTimeWins = 0;
		int numberOfOverTimeLosses = 0;
		int points = 0;
		int wins = 0;
		int losses = 0;
		int draws = 0;
		Team team = new Team();

		team.setTeamName(teamName);

		int numberOfGames = games.size();
		team.setNumberOfGames(numberOfGames);

		for (int i = 0; i < games.size(); i++) {
			Game game = games.get(i);

			if (game.isOverTime()) {
				draws++;
				numberOfOverTime++;
				if (game.getHomeTeamName().equals(teamName)) {
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
				if (game.getHomeTeamName().equals(teamName)) {
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

	public String getWinnerTeamName(Game game) {
		if (game.getHomeGoals() > game.getGuestGoals()) {
			return game.getHomeTeamName();
		} else {
			return game.getGuestTeamName();
		}
	}

	public List<Unit> getUnits(float begin, float end, int amount) {

		List<Unit> units = new ArrayList<>(amount * amount);

		float step = (end - begin) / amount;
		float startX = begin;
		float startY = begin;

		for (int i = 0; i < amount; i++) {

			float endY = startY + step;

			for (int j = 0; j < amount; j++) {
				Cell cell = new Cell();
				Unit unit = new Unit();

				cell.setyMax(endY);

				cell.setxMin(startX);
				cell.setxMax(startX + step);

				cell.setyMin(startY);
				cell.setyMax(endY);

				startX = startX + step;
				unit.setCell(cell);

				units.add(unit);
			}
			startX = begin;
			startY = startY + step;
		}

		return units;
	}

}
