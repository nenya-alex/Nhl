package ua.nenya.main;

import java.io.File;
import java.util.List;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.util.FillerFileNameList;
import ua.nenya.util.JsonConverter;

public class Main {

	public static void main(String[] args) {
		System.out.println("START");

		Controller controll = new Controller();
		JsonConverter<Game> converter = new JsonConverter<>();
		
		FillerFileNameList filler = new FillerFileNameList();
		List<String> files = filler.fillList();
		List<Dot> dots = new JsonConverter<Dot>().initDotsFromJson("src/main/resources/allDots.json");		
		
		for(String fileName: files){
			List<Game> games = new JsonConverter<Game>().initGamesFromJson("src/main/resources/"+fileName);
			controll.setNewOddsInGames(games, dots);
			converter.convertToJSON(games, new File("src/main/resources/allGamesWithNewOddsSeason"+fileName.substring(fileName.length()-7, fileName.length()-5)+".json"));
		}
		System.out.println("DONE");
	}

}
