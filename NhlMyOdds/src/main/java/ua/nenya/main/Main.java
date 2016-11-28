package ua.nenya.main;

import java.io.File;
import java.util.List;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.util.JsonConverter;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("START");
				
		Controller controll = new Controller();
		List<Game> games = new JsonConverter<Game>().initFromJson("src/main/resources/allGames2015.json");
		List<Dot> dots = new JsonConverter<Dot>().initFromJson("src/main/resources/allDots.json");
		
		System.out.println(games);

		controll.setNewOddsInGames(games, dots);
		
		new JsonConverter<Game>().convertToJSON(games, new File("allGamesWithNewOdds.json"));
		System.out.println("DONE");
	}

}
