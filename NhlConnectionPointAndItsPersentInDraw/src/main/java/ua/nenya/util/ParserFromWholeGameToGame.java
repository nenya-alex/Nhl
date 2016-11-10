package ua.nenya.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ua.nenya.domain.Game;
import ua.nenya.domain.WholeGame;

public class ParserFromWholeGameToGame {

	public List<Game> parse(List<WholeGame> wholeGames) {
		List<Game> games = new ArrayList<>();
		
		for(int i=wholeGames.size()-1; i>=0; i--){
			Game game = parseOneGame(wholeGames.get(i));
			game.setId(wholeGames.size()-i);
			games.add(game);
		}
		
		return games;
	}

	private Game parseOneGame(WholeGame wholeGame) {
		Game game = new Game();
		
		String players = wholeGame.getPlayers();
		
		String homeTeam = players.substring(0, players.indexOf("-")-1);
		game.setHomeTeamName(homeTeam);
		
		String guestTeam = players.substring(players.indexOf("-")+2,players.length());
		game.setGuestTeamName(guestTeam);
		
		game.setOddsHomeWin(Double.valueOf(wholeGame.getOddsHomeWin()));
		game.setOddsDraw(Double.valueOf(wholeGame.getOddsDraw()));
		game.setOddsGuestWin(Double.valueOf(wholeGame.getOddsGuestWin()));
		
		String score = wholeGame.getScore();
		if(score.endsWith(".")||score.endsWith("T")){
			game.setOverTime(true);
			game.setHomeGoals(Integer.valueOf(score.substring(0, score.indexOf(":"))));
			game.setGuestGoals(Integer.valueOf(score.substring(score.indexOf(":")+1, score.indexOf(" "))));
		}else{
			game.setOverTime(false);
			game.setHomeGoals(Integer.valueOf(score.substring(0, score.indexOf(":"))));
			game.setGuestGoals(Integer.valueOf(score.substring(score.indexOf(":")+1, score.length())));
		}
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		game.setDate(LocalDate.parse(wholeGame.getDate(),format));
		
		return game;
	}

}
