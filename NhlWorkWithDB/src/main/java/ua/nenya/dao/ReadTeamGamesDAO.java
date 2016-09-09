package ua.nenya.dao;

import java.time.LocalDate;
import java.util.List;

import ua.nenya.domain.Game;

public interface ReadTeamGamesDAO {
	List<Game> readTeam(String teamName, LocalDate date);
}