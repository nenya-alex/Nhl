package ua.nenya.main;

import java.io.File;
import java.util.Collections;
import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Profit;
import ua.nenya.domain.WholeGame;
import ua.nenya.domain.WholeWinDrawLoseStatistics;
import ua.nenya.util.Contstants;
import ua.nenya.util.FillerFileNameList;
import ua.nenya.util.JsonConverter;
import ua.nenya.util.ParserFromWholeGameToGame;
import ua.nenya.util.UnloaderInFile;

public class Main {

	private final static int BEGIN_OF_INTERVAL = 0;
	private final static int END_OF_INTERVAL = 164;
	private final static int NUMBER_OF_INTERVALS = 164;
	private static final int SIMPLE_PROBABILITY_CASE_ID = 0;
	private static final int BAYES_PROBABILITY_CASE_ID = 1;
	private static final double INITIAL_SUM = 1000.0;
	private static final double BET_SUM = 1.0;
	private final static String UNLOADING_HOME_WIN_DOTS_FILE_NAME = "dotsHomeWinPoints.csv";
	private final static String UNLOADING_DRAW_ET_DOTS_FILE_NAME = "dotsDrawETPoints.csv";
	private final static String UNLOADING_DRAW_PEN_DOTS_FILE_NAME = "dotsDrawPenPoints.csv";
	private final static String UNLOADING_GUEST_WIN_DOTS_FILE_NAME = "dotsHomeLostPoints.csv";

	public static void main(String[] args) {
		System.out.println("START");

		Calculator calculator = new Calculator();
		UnloaderInFile unloaderInFile = new UnloaderInFile();
		JsonConverter<WholeGame> jsonConverter = new JsonConverter<>();
		Controller controll = new Controller();
		FillerFileNameList filler = new FillerFileNameList();
		ParserFromWholeGameToGame parserFromWholeGameToGame = new ParserFromWholeGameToGame();
		
		List<String> filesForStatistic = filler.fillFileListForStatistics();
		List<List<WholeGame>> wholeGamesForStatistic = controll.initWholeGames(filesForStatistic);
		List<List<Game>> games = parserFromWholeGameToGame.parse(wholeGamesForStatistic);
		
		controll.fillGamesByStatistics(games);
		List<Dot> dots = controll.getAllDots(BEGIN_OF_INTERVAL, END_OF_INTERVAL, NUMBER_OF_INTERVALS, games);		
		controll.fillDotsByWinsLosesDraws(dots);
		WholeWinDrawLoseStatistics wholeWinDrawLoseStatistics = calculator.calculateWholeWinDrawLose(dots);
		
		List<String> filesForBetting = filler.fillFileListForBetting();
		List<List<WholeGame>> wholeGamesForBetting = controll.initWholeGames(filesForBetting);
		List<List<Game>> gamesForBetting = parserFromWholeGameToGame.parse(wholeGamesForBetting);
		controll.fillGamesByStatistics(gamesForBetting);
		
		controll.setNewOddsInGames(gamesForBetting, dots, wholeWinDrawLoseStatistics);
		
		List<Profit> profitsBySimpleProbability = controll.getProfitByCaseId(gamesForBetting, SIMPLE_PROBABILITY_CASE_ID, BET_SUM, INITIAL_SUM);
			
//			profitConverter.convertToJSON(profit, new File("profitSeason"+season+".json"));
		writeProfit(profitsBySimpleProbability);
//			unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_LOSES_DOTS, season, BEGIN_OF_INTERVAL, END_OF_INTERVAL, profit);
		List<Profit> profitsByBayesProbability = controll.getProfitByCaseId(gamesForBetting, BAYES_PROBABILITY_CASE_ID, BET_SUM, INITIAL_SUM);
		writeProfit(profitsByBayesProbability);
//		System.out.println(filledGamesForBetting.size());

		//// new JsonConverter<Dot>().convertToJSON(dots, new
		// File("allDots.json"));

		System.out.println("DONE");
	}

	private static void writeProfit(List<Profit> profits) {
		for(Profit profit:  profits){
			System.out.println("Season "+profit.getSeason()+" Wins% = "+(profit.getWinWins()+profit.getWinLost()+profit.getWinDraws())/(double)profit.getTotalSums().size());
			System.out.println("Season "+profit.getSeason()+" Loses% = "+(profit.getLostWins()+profit.getLostLost()+profit.getLostDraws())/(double)profit.getTotalSums().size());
			System.out.println("Season "+profit.getSeason()+" Done bets = "+profit.getTotalSums().size());
			double profitSum = profit.getTotalSum()-INITIAL_SUM;
			System.out.println("Season "+profit.getSeason()+" Sum Wins% = "+(profit.getWinSumOfWins())/(double)profitSum);
			System.out.println("Season "+profit.getSeason()+" Sum Draws% = "+(profit.getWinSumOfDraws())/(double)profitSum);
			System.out.println("Season "+profit.getSeason()+" Sum Loses% = "+(profit.getWinSumOfLost())/(double)profitSum);
			System.out.println("Season "+profit.getSeason()+" Final TotalSum = "+profit.getTotalSum());
			System.out.println("-----------------------------------------");
		}
	}

}
