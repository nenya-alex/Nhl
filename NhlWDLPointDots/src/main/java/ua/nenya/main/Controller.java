package ua.nenya.main;

import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.dao.impl.ReadTeamGames;
import ua.nenya.dao.impl.JsonConverter;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;
import ua.nenya.domain.WholeGame;
import ua.nenya.util.ParserFromWholeGameToGame;

public class Controller {
	
	public void getAllDots(String fileName, List<Dot> dots) {
		
		ReadTeamGames readGame = new ReadTeamGames();
		Calculator calculator = new Calculator();

		List<WholeGame> wholeGames = new JsonConverter().initWholeGamesFromJson(fileName);		
		List<Game> games = new ParserFromWholeGameToGame().parse(wholeGames);

		for (int id = 0; id < games.size(); id++) {
			Game game = games.get(id);
			
			String homeTeamName = game.getHomeTeamName();
			List<Game> homeTeamGames = readGame.getAllGamesByTeamName(homeTeamName, id, games);

			String guestTeamName = game.getGuestTeamName();
			List<Game> guestTeamGames = readGame.getAllGamesByTeamName(guestTeamName, id, games);

			Team homeTeam = calculator.calculateTeamStat(homeTeamGames, homeTeamName);

			Team guestTeam = calculator.calculateTeamStat(guestTeamGames, guestTeamName);
			
			int homeTeamRating = homeTeam.getPoints();
			int guestTeamRating = guestTeam.getPoints();
			
			if (!game.isOverTime()) {
				if(calculator.isHomeTeamWinner(game)){
					calculator.fillDotsByCount(dots, id, fileName, homeTeamRating, guestTeamRating, "homeWinner");
				}else{
					calculator.fillDotsByCount(dots, id, fileName, guestTeamRating, homeTeamRating, "guestWinner");
				}
			}else{
				calculator.fillDotsByCount(dots, id, fileName, homeTeamRating, guestTeamRating, "draw");				
			}			
		}
	}
}
