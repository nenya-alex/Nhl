package ua.nenya.main;

import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;
import ua.nenya.util.Contstants;
import ua.nenya.util.JsonConverter;

public class Controller {
	
	public void setNewOddsInGames(List<Game> games2, List<Dot> dots) {
		
//		Calculator calculator = new Calculator();
//
//		List<WholeGame> wholeGames = new JsonConverter().initFromJson(games2);		
//		List<Game> games = new ParserFromWholeGameToGame().parse(wholeGames);
//
//		for (int id = 0; id < games.size(); id++) {
//			Game game = games.get(id);
//
//			Team homeTeam = calculator.calculateTeamStatistic(games, game.getHomeTeam().getTeamName(), id);
//			Team guestTeam = calculator.calculateTeamStatistic(games, game.getGuestTeam().getTeamName(), id);			
//			
//			game.setHomeTeam(homeTeam);
//			game.setGuestTeam(guestTeam);
//			
//			if (!game.isOverTime()) {
//				if(calculator.isHomeTeamWinner(game)){
//					calculator.fillDotsByGames(dots, game, Contstants.HOME_WINER);
//				}else{
//					calculator.fillDotsByGames(dots, game, Contstants.HOME_LOSER);
//				}
//			}else{
//				if(game.getPenalty()){
//					calculator.fillDotsByGames(dots, game, Contstants.DRAW_PEN);
//				}else{
//					calculator.fillDotsByGames(dots, game, Contstants.DRAW_ET);
//				}							
//			}			
//		}
	}
}
