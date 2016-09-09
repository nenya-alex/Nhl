package ua.nenya.dao.impl;

import java.util.List;

import ua.nenya.domain.Game;

public interface InsertInTableDAO {
	
	void insert(List<Game> games, String tableName);

}
