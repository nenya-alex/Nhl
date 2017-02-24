package ua.nenya.main;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.dao.ReadTeamGamesDAO;
import ua.nenya.dao.impl.ReadTeamGamesDAOImpl;
import ua.nenya.domain.Game;
import ua.nenya.domain.PairOfTeams;
import ua.nenya.domain.Team;

public class Runner {
	private ReadTeamGamesDAO readGame = new ReadTeamGamesDAOImpl();
	private Calculator calculator = new Calculator();
			
	public void runApp(long id, String tableName){
		
		Game game = readGame.readGameById(id, tableName);
		
		String homeTeamName = game.getHomeTeamName();
		List<Game> homeTeamGames = readGame.getAllGamesByTeamName(homeTeamName, id, tableName);
		
		String guestTeamName = game.getGuestTeamName();
		List<Game> guestTeamGames = readGame.getAllGamesByTeamName(guestTeamName, id, tableName);
		
		Team homeTeam = calculator.calculateTeamStat(homeTeamGames, homeTeamName);
		homeTeam.setId(game.getId());
		homeTeam.setOddsOfWin(game.getOddsHomeWin());
		homeTeam.setOddsOfDraw(game.getOddsDraw());
		homeTeam.setOddsOfLose(game.getOddsGuestWin());
		
		Team guestTeam = calculator.calculateTeamStat(guestTeamGames, guestTeamName);
		guestTeam.setId(game.getId());
		guestTeam.setOddsOfWin(game.getOddsGuestWin());
		guestTeam.setOddsOfDraw(game.getOddsDraw());
		guestTeam.setOddsOfLose(game.getOddsHomeWin());
		
		PairOfTeams pairOfTeams = new PairOfTeams();
		pairOfTeams.setHomeTeam(homeTeam);
		pairOfTeams.setGuestTeam(guestTeam);
		
		calculator.calculateNewOdss(pairOfTeams);
		
		homeTeam.setNewOddsOfWin(pairOfTeams.getHomeTeam().getNewOddsOfWin());
		homeTeam.setNewOddsOfDraw(pairOfTeams.getHomeTeam().getNewOddsOfDraw());
		homeTeam.setNewOddsOfLose(pairOfTeams.getHomeTeam().getNewOddsOfLose());
		
		guestTeam.setNewOddsOfWin(pairOfTeams.getGuestTeam().getNewOddsOfWin());
		guestTeam.setNewOddsOfDraw(pairOfTeams.getGuestTeam().getNewOddsOfDraw());
		guestTeam.setNewOddsOfLose(pairOfTeams.getGuestTeam().getNewOddsOfLose());
		
		List<Double> newOddsOfWin = new ArrayList<>();
		List<Double> newOddsOfDraw = new ArrayList<>();
		List<Double> newOddsOfLose = new ArrayList<>();
		
		newOddsOfWin.add(homeTeam.getNewOddsOfWin());
		newOddsOfWin.add(guestTeam.getNewOddsOfLose());
		
		newOddsOfDraw.add(homeTeam.getNewOddsOfDraw());
		newOddsOfDraw.add(guestTeam.getNewOddsOfDraw());
		
		newOddsOfLose.add(homeTeam.getNewOddsOfLose());
		newOddsOfLose.add(guestTeam.getNewOddsOfWin());
		
		game.setNewOddsOfWin(newOddsOfWin);
		game.setNewOddsOfDraw(newOddsOfDraw);
		game.setNewOddsOfLose(newOddsOfLose);
		
		System.out.println(game);
		System.out.println("---------------------------");
		
//		System.out.println(homeTeam);
//		System.out.println(" ");
//		System.out.println(guestTeam);
	}

}
