package ua.nenya.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;

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

	public void fillDotsByCount(List<Dot> dots, int id, String fileName, int homeTeamRating, int guestTeamRating, String result) {
		for(Dot dot: dots){
			if(homeTeamRating == dot.getX() && guestTeamRating == dot.getY()){
				dot.setSeason(fileName);
				dot.setGameId(id);
				Map<String,Integer> resultMap = dot.getResult();
				for(Map.Entry<String, Integer> pair: resultMap.entrySet()){
					if(pair.getKey().equals(result)){
						Integer count = pair.getValue();
						count++;
						pair.setValue(count);
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

}
