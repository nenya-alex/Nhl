package ua.nenya.main;

import java.io.File;
import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;
import ua.nenya.domain.WholeGame;
import ua.nenya.util.Contstants;
import ua.nenya.util.JsonConverter;
import ua.nenya.util.ParserFromWholeGameToGame;

public class Controller {
	
	public void getAllDots(String fileName, List<Dot> dots) {
		
		Calculator calculator = new Calculator();

		List<WholeGame> wholeGames = new JsonConverter<WholeGame>().initWholeGamesFromJson(fileName);	
		List<Game> games = new ParserFromWholeGameToGame().parse(wholeGames);		
		System.out.println(fileName.substring(fileName.length()-7, fileName.length()-5));

		for (int id = 0; id < games.size(); id++) {
			Game game = games.get(id);
			game.setSeason(fileName.substring(fileName.length()-7, fileName.length()-5));

			Team homeTeam = calculator.calculateTeamStatistic(games, game.getHomeTeam().getTeamName(), id);
			Team guestTeam = calculator.calculateTeamStatistic(games, game.getGuestTeam().getTeamName(), id);			
			
			game.setHomeTeam(homeTeam);
			game.setGuestTeam(guestTeam);			
			
			if (!game.isOverTime()) {
				if(calculator.isHomeTeamWinner(game)){
					calculator.fillDotsByGames(dots, game, Contstants.HOME_WINER);
				}else{
					calculator.fillDotsByGames(dots, game, Contstants.HOME_LOSER);
				}
			}else{
				if(game.getPenalty()){
					if(game.getHomeGoals()>game.getGuestGoals()){
						calculator.fillDotsByGames(dots, game, Contstants.DRAW_WIN_PEN);
					}else{
						calculator.fillDotsByGames(dots, game, Contstants.DRAW_LOSE_PEN);
					}
				}else{
					if(game.getHomeGoals()>game.getGuestGoals()){
						calculator.fillDotsByGames(dots, game, Contstants.DRAW_WIN_ET);
					}else{
						calculator.fillDotsByGames(dots, game, Contstants.DRAW_LOSE_ET);
					}					
				}							
			}			
		}
		String fileNameForAllGamesSeason = "allGames"+fileName.substring(22, 24)+".json";
		new JsonConverter<Game>().convertToJSON(games, new File(fileNameForAllGamesSeason));
	}
}
