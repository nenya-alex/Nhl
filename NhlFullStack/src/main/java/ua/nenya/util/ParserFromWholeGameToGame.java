package ua.nenya.util;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.domain.Game;
import ua.nenya.domain.WholeGame;

public class ParserFromWholeGameToGame {

	public List<List<Game>> parse(List<List<WholeGame>> wholeGames) {
		List<List<Game>> games = new ArrayList<>();
		
		for(List<WholeGame> itWholeGames: wholeGames){
			List<Game> itGames = new ArrayList<>();
			for(int i=itWholeGames.size()-1; i>=0; i--){
				Game game = parseOneGame(itWholeGames.get(i));
				game.setId(itWholeGames.size()-i);
				itGames.add(game);
			}
			games.add(itGames);
		}		
		return games;
	}

	private Game parseOneGame(WholeGame wholeGame) {
		Game game = new Game();
		
		String players = wholeGame.getPlayers();
		
		String homeTeam = players.substring(0, players.indexOf("-")-1);
		game.getHomeTeam().setTeamName(homeTeam);
		
		String guestTeam = players.substring(players.indexOf("-")+2,players.length());
		game.getGuestTeam().setTeamName(guestTeam);
		
		game.setOddsHomeWin(Double.valueOf(wholeGame.getOddsHomeWin()));
		game.setOddsDraw(Double.valueOf(wholeGame.getOddsDraw()));
		game.setOddsHomeLose(Double.valueOf(wholeGame.getOddsGuestWin()));
		
		String score = wholeGame.getScore();
		if(score.endsWith(".")||score.endsWith("T")){
			game.setOverTime(true);
			game.setHomeGoals(Integer.valueOf(score.substring(0, score.indexOf(":"))));
			game.setGuestGoals(Integer.valueOf(score.substring(score.indexOf(":")+1, score.indexOf(" "))));
			if(score.endsWith(".")){
				game.setPenalty(true);
			}else{
				game.setPenalty(false);
			}
		}else{
			game.setOverTime(false);
			game.setHomeGoals(Integer.valueOf(score.substring(0, score.indexOf(":"))));
			game.setGuestGoals(Integer.valueOf(score.substring(score.indexOf(":")+1, score.length())));
		}
		
		game.setDate(wholeGame.getDate());
		game.setSeason(wholeGame.getSeason());
		return game;
	}

}
