package ua.nenya.dao;

import java.util.List;

import ua.nenya.domain.Game;

public interface ReadTeamGamesDAO {
	Game readGameById(long id, String tableName);
	List<Game> getAllGamesByTeamName(String team, long id, String tableName);
}
