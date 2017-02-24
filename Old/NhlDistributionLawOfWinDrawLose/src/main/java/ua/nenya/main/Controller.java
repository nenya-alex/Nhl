package ua.nenya.main;

import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.dao.impl.ReadTeamGames;
import ua.nenya.dao.impl.WholeGameFileDAOImpl;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;
import ua.nenya.domain.Unit;
import ua.nenya.domain.WholeGame;
import ua.nenya.util.ParserFromWholeGameToGame;

public class Controller {
	
	public List<Dot> getAllDotsOfWins(List<Unit> units, String fileName, List<Dot> dotsOfWins) {
		
		ReadTeamGames readGame = new ReadTeamGames();
		Calculator calculator = new Calculator();

		List<WholeGame> wholeGames = new WholeGameFileDAOImpl().initWholeGamesFromJsonFile(fileName);		
		List<Game> games = new ParserFromWholeGameToGame().parse(wholeGames);
		int sizeDotsOfWins = dotsOfWins.size();

		for (int id = (int) (games.size()*0.1); id < games.size(); id++) {
			Game game = games.get(id);
			Dot dot = new Dot();
			dot.setGameId(id);
			dot.setSeason(fileName);
			
			String homeTeamName = game.getHomeTeamName();
			List<Game> homeTeamGames = readGame.getAllGamesByTeamName(homeTeamName, id, games);

			String guestTeamName = game.getGuestTeamName();
			List<Game> guestTeamGames = readGame.getAllGamesByTeamName(guestTeamName, id, games);

			Team homeTeam = calculator.calculateTeamStat(homeTeamGames, homeTeamName);

			Team guestTeam = calculator.calculateTeamStat(guestTeamGames, guestTeamName);
			
			float homeTeamRating = homeTeam.getPointPstg();
			float guestTeamRating = guestTeam.getPointPstg();
			
			if (!game.isOverTime()) {
				String winnerTeamName = calculator.getWinnerTeamName(game);				
				Dot dotOfWin = calculator.getDotOfWin(dot, winnerTeamName, homeTeamName, homeTeamRating, guestTeamRating);
//				calculator.fillUnitCellsByCount(units, winnerTeamName, homeTeamName, homeTeamRating, guestTeamRating);	
				dotsOfWins.add(dotOfWin);
			}else{
				dot.setX(homeTeamRating);
				dot.setY(guestTeamRating);
				dot.setResult("draw");
				dotsOfWins.add(dot);
			}
			
		}
		System.out.println("Number of wins in season "+fileName+" = "+(dotsOfWins.size()-sizeDotsOfWins));
		calculator.fillUnitCellsByCounts(units);
		calculator.fillUnitCellsByAverage(units);
		
		for (Unit unit : units) {
			unit.setCount(0);
		}
		return dotsOfWins;
	}
}
