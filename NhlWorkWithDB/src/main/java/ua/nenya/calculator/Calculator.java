package ua.nenya.calculator;

import java.util.List;

import ua.nenya.domain.Game;
import ua.nenya.domain.PairOfTeams;
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
	
	public PairOfTeams calculateNewOdss (final PairOfTeams pairOfTeams){
		
		PairOfTeams pairOfTeamsWithNewOdds = new PairOfTeams();
		
		pairOfTeamsWithNewOdds.setHomeTeam(pairOfTeams.getHomeTeam());		
		pairOfTeamsWithNewOdds.setGuestTeam(pairOfTeams.getGuestTeam());		
		
		getNewOdds(pairOfTeamsWithNewOdds);
		
		return pairOfTeamsWithNewOdds;
		
	}

	private void getNewOdds(PairOfTeams pairOfTeamsWithNewOdds) {
		Team homeTeam = pairOfTeamsWithNewOdds.getHomeTeam();
		Team guestTeam = pairOfTeamsWithNewOdds.getGuestTeam();
		
		double zWinHomeCoef = getZWinCoef(homeTeam, guestTeam);
		double zDrawHomeCoef = getZDrawCoef(homeTeam, guestTeam);
		double zLoseHomeCoef = getZLoseCoef(homeTeam, guestTeam);
		
		double zWinGuestCoef = zLoseHomeCoef;
		double zDrawGuestCoef = zDrawHomeCoef;
		double zLoseGuestCoef = zWinHomeCoef;
		
		double newHomeWinOdds = getNewWinOdds(homeTeam, zWinHomeCoef, zDrawHomeCoef, zLoseHomeCoef);
		homeTeam.setNewOddsOfWin(newHomeWinOdds);
		double newHomeDrawOdds = getNewDrawOdds(homeTeam, zWinHomeCoef, zDrawHomeCoef, zLoseHomeCoef);
		homeTeam.setNewOddsOfDraw(newHomeDrawOdds);
		double newHomeLoseOdds = getNewLoseOdds(homeTeam, zWinHomeCoef, zDrawHomeCoef, zLoseHomeCoef);
		homeTeam.setNewOddsOfLose(newHomeLoseOdds);
		
		double newGuestWinOdds = getNewWinOdds(guestTeam, zWinGuestCoef, zDrawGuestCoef, zLoseGuestCoef);
		guestTeam.setNewOddsOfWin(newGuestWinOdds);
		double newGuestDrawOdds = getNewDrawOdds(guestTeam, zWinGuestCoef, zDrawGuestCoef, zLoseGuestCoef);
		guestTeam.setNewOddsOfDraw(newGuestDrawOdds);
		double newGuestLoseOdds = getNewLoseOdds(guestTeam, zWinGuestCoef, zDrawGuestCoef, zLoseGuestCoef);
		guestTeam.setNewOddsOfLose(newGuestLoseOdds);
		
	}

	private double getNewLoseOdds(Team team, double zWinCoef, double zDrawCoef, double zLoseCoef) {
		return (team.getWins()*zWinCoef+team.getDraws()*zDrawCoef+team.getLosses()*zLoseCoef)/(team.getLosses()*zLoseCoef);
	}

	private double getNewDrawOdds(Team team, double zWinCoef, double zDrawCoef, double zLoseCoef) {
		return (team.getWins()*zWinCoef+team.getDraws()*zDrawCoef+team.getLosses()*zLoseCoef)/(team.getDraws()*zDrawCoef);
	}

	private double getNewWinOdds(Team team, double zWinCoef, double zDrawCoef, double zLoseCoef) {
		return (team.getWins()*zWinCoef+team.getDraws()*zDrawCoef+team.getLosses()*zLoseCoef)/(team.getWins()*zWinCoef);
	}

	private double getZLoseCoef(Team teamOne, Team teamTwo) {
		double z_0 = 0.333;
		double a = 0.7266;
		double xNew = teamOne.getPointPstg();
		double yNew = teamTwo.getPointPstg();	
		double yOld = -0.7*xNew+0.7*yNew; 
		double zLoseCoef = z_0*Math.exp(0.5)*Math.exp(-Math.pow((yOld-a), 2)/(2*a*a));
		return zLoseCoef;
	}

	private double getZDrawCoef(Team teamOne, Team teamTwo) {
		double z_0 = 0.333;
		double xNew = teamOne.getPointPstg();
		double yNew = teamTwo.getPointPstg();	
		double yOld = -0.7*xNew+0.7*yNew; 
		double zDrawCoef = z_0*Math.exp(-z_0*z_0*Math.PI*yOld*yOld);
		return zDrawCoef;
	}

	private double getZWinCoef(Team teamOne, Team teamTwo) {
		double z_0 = 0.333;
		double a = 0.7266;
		double xNew = teamOne.getPointPstg();
		double yNew = teamTwo.getPointPstg();	
		double yOld = -0.7*xNew+0.7*yNew; 
		double zLoseCoef = z_0*Math.exp(0.5)*Math.exp(-Math.pow((yOld+a), 2)/(2*a*a));
		return zLoseCoef;
	}

}
