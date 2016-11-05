package ua.nenya.main;

import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.dao.impl.ReadTeamGames;
import ua.nenya.dao.impl.WholeGameFileDAOImpl;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;
import ua.nenya.domain.WholeGame;
import ua.nenya.util.ParserFromWholeGameToGame;

public class Controller {
	
	public void getAllDots(String fileName, List<Dot> dotsOfHomeTeam, List<Dot> dotsOfGuestTeam) {
		
		ReadTeamGames readGame = new ReadTeamGames();
		Calculator calculator = new Calculator();

		List<WholeGame> wholeGames = new WholeGameFileDAOImpl().initWholeGamesFromJsonFile(fileName);		
		List<Game> games = new ParserFromWholeGameToGame().parse(wholeGames);

		for (int id = (int) (games.size()*0.1); id < games.size(); id++) {
			Game game = games.get(id);
			Dot homeDot = new Dot();
			homeDot.setGameId(id);
			homeDot.setSeason(fileName);
			
			String homeTeamName = game.getHomeTeamName();
			List<Game> homeTeamGames = readGame.getAllGamesByTeamName(homeTeamName, id, games);

			String guestTeamName = game.getGuestTeamName();
			List<Game> guestTeamGames = readGame.getAllGamesByTeamName(guestTeamName, id, games);

			Team homeTeam = calculator.calculateTeamStat(homeTeamGames, homeTeamName);
			Team guestTeam = calculator.calculateTeamStat(guestTeamGames, guestTeamName);

			fillDotListWithDots(dotsOfHomeTeam, homeDot, homeTeam);
			
			Dot guestDot = new Dot();
			guestDot.setGameId(id);
			guestDot.setSeason(fileName);
			fillDotListWithDots(dotsOfGuestTeam, guestDot, guestTeam);
			
		}
	}

	private void fillDotListWithDots(List<Dot> dots, Dot dot, Team team) {
		dot.setX(team.getPoints());
		dot.setY(team.getPointPstg());
		
		dots.add(dot);
	}
}
