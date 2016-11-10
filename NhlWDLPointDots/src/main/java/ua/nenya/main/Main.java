package ua.nenya.main;

import java.io.File;
import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.dao.impl.JsonConverter;
import ua.nenya.domain.Dot;
import ua.nenya.util.FillerFileNameList;
import ua.nenya.util.UnloaderInFile;

public class Main {
	
	private final static int BEGIN_OF_INTERVAL = 0;
	private final static int END_OF_INTERVAL = 164;
	private final static int NUMBER_OF_INTERVALS = 164;
	private final static String UNLOADING_HOME_WIN_DOTS_FILE_NAME = "dotsHomeWinPoints.csv";
	private final static String UNLOADING_DRAW_DOTS_FILE_NAME = "dotsDrawPoints.csv";
	private final static String UNLOADING_GUEST_WIN_DOTS_FILE_NAME = "dotsGuestWinPoints.csv";

	public static void main(String[] args) {
		System.out.println("START");
		Calculator calculator = new Calculator();
		UnloaderInFile unloaderInFile = new UnloaderInFile();

		FillerFileNameList filler = new FillerFileNameList();
		List<String> files = filler.fillList();
		
		Controller controll = new Controller();
		
		List<Dot> dots = calculator.getDots(BEGIN_OF_INTERVAL, END_OF_INTERVAL, NUMBER_OF_INTERVALS);

		for(String fileName: files){
			controll.getAllDots(fileName, dots);
		}
		
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_HOME_WIN_DOTS_FILE_NAME, BEGIN_OF_INTERVAL, END_OF_INTERVAL, dots, "homeWinner");
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_DRAW_DOTS_FILE_NAME, BEGIN_OF_INTERVAL, END_OF_INTERVAL, dots, "draw");
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_GUEST_WIN_DOTS_FILE_NAME, BEGIN_OF_INTERVAL, END_OF_INTERVAL, dots, "guestWinner");
		
		new JsonConverter().convertDotsToJSON(dots, new File("allDots.json"));
		
		System.out.println("DONE");
	}

}
