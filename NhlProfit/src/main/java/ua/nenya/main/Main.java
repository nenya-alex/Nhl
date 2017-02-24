package ua.nenya.main;

import java.io.File;
import java.util.List;

import ua.nenya.domain.Game;
import ua.nenya.domain.Profit;
import ua.nenya.util.Contstants;
import ua.nenya.util.FillerFileNameList;
import ua.nenya.util.JsonConverter;
import ua.nenya.util.UnloaderInFile;

public class Main {

	private static final double INITIAL_SUM = 1000.0;
	private static final double BET_SUM = 1.0;
	private static final int CASE_ID = 0;
	private final static String UNLOADING_LOSES_DOTS = "DotsSeason";
	private final static int BEGIN_OF_INTERVAL = 0;
	private final static int END_OF_INTERVAL = 164;
	private final static int NUMBER_OF_INTERVALS = 164;

	public static void main(String[] args) {
		System.out.println("START");

		Controller controll = new Controller();
		JsonConverter<Game> gameConverter = new JsonConverter<>();
		JsonConverter<Profit> profitConverter = new JsonConverter<>();
		UnloaderInFile unloaderInFile = new UnloaderInFile();
		
		FillerFileNameList filler = new FillerFileNameList();
		List<String> files = filler.fillList();
		
		for(String fileName: files){
			String season = fileName.substring(fileName.length()-7, fileName.length()-5);
			List<Game> games = gameConverter.initGamesFromJson("src/main/resources/"+fileName);
			Profit profit = controll.getProfitByCaseId(games, CASE_ID, BET_SUM, INITIAL_SUM);
			profitConverter.convertToJSON(profit, new File("profitSeason"+season+".json"));
			System.out.println("Season "+season+" Wins% = "+(profit.getWinWins()+profit.getWinLost()+profit.getWinDraws())/(double)profit.getTotalSums().size());
			System.out.println("Season "+season+" Loses% = "+(profit.getLostWins()+profit.getLostLost()+profit.getLostDraws())/(double)profit.getTotalSums().size());
			System.out.println("Season "+season+" Done bets = "+profit.getTotalSums().size());
			System.out.println("Season "+season+" Sum Wins% = "+(profit.getWinSumOfWins())/(double)(profit.getTotalSum()-INITIAL_SUM));
			System.out.println("Season "+season+" Sum Draws% = "+(profit.getWinSumOfDraws())/(double)(profit.getTotalSum()-INITIAL_SUM));
			System.out.println("Season "+season+" Sum Loses% = "+(profit.getWinSumOfLost())/(double)(profit.getTotalSum()-INITIAL_SUM));
			System.out.println("Season "+season+" Final TotalSum = "+profit.getTotalSum());
			System.out.println("-----------------------------------------");
			unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_LOSES_DOTS, season, BEGIN_OF_INTERVAL, END_OF_INTERVAL, profit);
		}
		
		System.out.println("DONE");
	}

}
