package ua.nenya.main;

import java.io.File;
import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Dot;
import ua.nenya.util.Contstants;
import ua.nenya.util.FillerFileNameList;
import ua.nenya.util.JsonConverter;
import ua.nenya.util.UnloaderInFile;

public class Main {
	
	private final static int BEGIN_OF_INTERVAL = 0;
	private final static int END_OF_INTERVAL = 164;
	private final static int NUMBER_OF_INTERVALS = 164;
	private final static String UNLOADING_HOME_WIN_DOTS_FILE_NAME = "dotsHomeWinPoints.csv";
	private final static String UNLOADING_DRAW_ET_DOTS_FILE_NAME = "dotsDrawETPoints.csv";
	private final static String UNLOADING_DRAW_PEN_DOTS_FILE_NAME = "dotsDrawPenPoints.csv";
	private final static String UNLOADING_GUEST_WIN_DOTS_FILE_NAME = "dotsHomeLostPoints.csv";

	public static void main(String[] args) {
		System.out.println("START");
		Calculator calculator = new Calculator();
		UnloaderInFile unloaderInFile = new UnloaderInFile();

		FillerFileNameList filler = new FillerFileNameList();
		List<String> filesForStatistics = filler.fillFileListForStstistics();
		
		Controller controll = new Controller();
		
		List<Dot> dots = calculator.getDots(BEGIN_OF_INTERVAL, END_OF_INTERVAL, NUMBER_OF_INTERVALS);

		for(String fileName: filesForStatistics){
			controll.getAllDots(fileName, dots);
		}
		
		for(Dot dot: dots){
			dot.setWins(dot.getResult().get(Contstants.HOME_WINER).size());
			dot.setLost(dot.getResult().get(Contstants.HOME_LOSER).size());
			dot.setDrawWinET(dot.getResult().get(Contstants.DRAW_WIN_ET).size());
			dot.setDrawLoseET(dot.getResult().get(Contstants.DRAW_LOSE_ET).size());
			dot.setDrawWinPen(dot.getResult().get(Contstants.DRAW_WIN_PEN).size());
			dot.setDrawLosePen(dot.getResult().get(Contstants.DRAW_LOSE_PEN).size());
		}
		
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_HOME_WIN_DOTS_FILE_NAME, BEGIN_OF_INTERVAL, END_OF_INTERVAL, dots, Contstants.HOME_WINER);
//		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_DRAW_ET_DOTS_FILE_NAME, BEGIN_OF_INTERVAL, END_OF_INTERVAL, dots, Contstants.DRAW_ET);
//		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_DRAW_PEN_DOTS_FILE_NAME, BEGIN_OF_INTERVAL, END_OF_INTERVAL, dots, Contstants.DRAW_PEN);
		unloaderInFile.unloadDotsOfWinsInFile(UNLOADING_GUEST_WIN_DOTS_FILE_NAME, BEGIN_OF_INTERVAL, END_OF_INTERVAL, dots, Contstants.HOME_LOSER);
		
//		new JsonConverter<Dot>().convertToJSON(dots, new File("allDots.json"));
		
		System.out.println("DONE");
	}

}
