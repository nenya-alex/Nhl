package ua.nenya.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.domain.Game;

public class ReadTeamGames {

	public List<Game> getAllGamesByTeamName(String team, long id, List<Game> games) {
		
		List<Game> teamGames = new ArrayList<>();
		
		for(Game game: games){
			String homeTeamName = game.getHomeTeamName();
			String guestTeamName = game.getGuestTeamName();
			long gameId = game.getId();
			
			if((team.equals(homeTeamName)||team.equals(guestTeamName))&& gameId<id ){
				teamGames.add(game);
			}
		}
		return teamGames;
	}

}
