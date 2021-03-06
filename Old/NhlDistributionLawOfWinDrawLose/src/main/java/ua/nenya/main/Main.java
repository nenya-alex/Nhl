package ua.nenya.main;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Unit;
import ua.nenya.util.FillerFileNameList;
import ua.nenya.util.UnloaderInFile;

public class Main {
	
	private final static float BEGIN_INTERVAL = 0;
	private final static float END_INTERVAL = 1;
	private final static int NUMBER_OF_INTERVALS = 10;
	private final static String UNLOADING_HOME_WIN_DOTS_FILE_NAME = "dotsHomeWin.csv";
	private final static String UNLOADING_DRAW_DOTS_FILE_NAME = "dotsDraw.csv";
	private final static String UNLOADING_GUEST_WIN_DOTS_FILE_NAME = "dotsGuestWin.csv";

	public static void main(String[] args) {
		System.out.println("START");
		Calculator calculator = new Calculator();
		UnloaderInFile unloaderInFile = new UnloaderInFile();
		List<Dot> dotsOfWins = new ArrayList<>();

		FillerFileNameList filler = new FillerFileNameList();
		List<String> files = filler.fillList();
		
		Controller controll = new Controller();
		
		List<Unit> unitsForSeasons = calculator.getUnits(BEGIN_INTERVAL, END_INTERVAL, NUMBER_OF_INTERVALS);
//		List<Unit> unitsForAll = calculator.getUnits(BEGIN_INTERVAL, END_INTERVAL, NUMBER_OF_INTERVALS);

		for(String fileName: files){
			controll.getAllDotsOfWins(unitsForSeasons, fileName, dotsOfWins);
		}
		
//		for(Dot dot: dotsOfWins){
//			calculator.setCountInUnit(unitsForAll, dot);
//		}
		
		System.out.println("Size dotsOfWins = "+dotsOfWins.size());
		
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_HOME_WIN_DOTS_FILE_NAME, dotsOfWins, "win");
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_DRAW_DOTS_FILE_NAME, dotsOfWins, "draw");
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_GUEST_WIN_DOTS_FILE_NAME, dotsOfWins, "lose");
		
		System.out.println("DONE");
	}

}
