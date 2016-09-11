package ua.nenya.calculator;

import java.util.List;

import ua.nenya.domain.Game;
import ua.nenya.domain.Team;

public class Calculator {
	
	public Team calculateTeamStat(List<Game> games, String teamName){
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
			
			if(game.isOverTime()){
				draws++;
				numberOfOverTime++;
				if(game.getHomeTeamName().equals(teamName)){
					if(game.getHomeGoals()>game.getGuestGoals()){
						points=points+2;
						numberOfOverTimeWins++;
					}else{
						points++;
						numberOfOverTimeLosses++;
					}
				}else{
					if(game.getHomeGoals()<game.getGuestGoals()){
						points=points+2;
						numberOfOverTimeWins++;
					}else{
						points++;
						numberOfOverTimeLosses++;
					}
				}
			}else{
				if(game.getHomeTeamName().equals(teamName)){
					if(game.getHomeGoals()>game.getGuestGoals()){
						points=points+2;
						wins++;
					}else{
						losses++;
					}
				}else{
					if(game.getHomeGoals()<game.getGuestGoals()){
						points=points+2;
						wins++;
					}else{
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
		team.setPointPstg((double)points/((double)numberOfGames*2));
				
		return team;
		
	}

}
