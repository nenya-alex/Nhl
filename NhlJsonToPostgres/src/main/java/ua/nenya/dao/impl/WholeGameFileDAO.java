package ua.nenya.dao.impl;

import java.util.List;

import ua.nenya.domain.WholeGame;

public interface WholeGameFileDAO {

	List<WholeGame> initWholeGamesFromJsonFile(String fileName); 
}
