package ua.nenya.main;

import java.util.List;

import ua.nenya.dao.impl.CreateTableDAO;
import ua.nenya.dao.impl.CreateTableDAOImpl;
import ua.nenya.dao.impl.InsertInTableDAO;
import ua.nenya.dao.impl.InsertInTableDAOImpl;
import ua.nenya.dao.impl.WholeGameFileDAO;
import ua.nenya.dao.impl.WholeGameFileDAOImpl;
import ua.nenya.domain.Game;
import ua.nenya.domain.WholeGame;
import ua.nenya.util.ParserFromWholeGameToGame;

public class GameMainStart {
	public final static String FILE_NAME = "nhl15-16.json";
	public final static String TABLE_NAME = "nhl2015";
	
	public static void main(String[] args) {
		System.out.println("Start");
		WholeGameFileDAO wholeGameFileDAO = new WholeGameFileDAOImpl();
		
		List<WholeGame> wholeGames = wholeGameFileDAO.initWholeGamesFromJsonFile(FILE_NAME);
		
		CreateTableDAO createTable = new CreateTableDAOImpl();
		
		createTable.createTable(TABLE_NAME);
		
		ParserFromWholeGameToGame parser = new ParserFromWholeGameToGame();
		
		List<Game> games = parser.parse(wholeGames);
		
		InsertInTableDAO insert = new InsertInTableDAOImpl();
		insert.insert(games, TABLE_NAME);
		System.out.println("DONE");
	}

}
