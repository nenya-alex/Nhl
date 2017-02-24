package ua.nenya.main;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.domain.Dot;
import ua.nenya.util.FillerFileNameList;
import ua.nenya.util.UnloaderInFile;

public class Main {
	
	private final static String FILE_NAME_HOME = "PPDrawHomeTeam.csv";
	private final static String FILE_NAME_GUEST = "PPDrawGuestTeam.csv";

	public static void main(String[] args) {
		System.out.println("START");
		UnloaderInFile unloaderInFile = new UnloaderInFile();
		List<Dot> dotsOfPointsPersentHome = new ArrayList<>();
		List<Dot> dotsOfPointsPersentGuest = new ArrayList<>();

		FillerFileNameList filler = new FillerFileNameList();
		List<String> files = filler.fillList();
		
		Controller controll = new Controller();
		

		for(String fileName: files){
			controll.getAllDots(fileName, dotsOfPointsPersentHome, dotsOfPointsPersentGuest);
		}
				
		unloaderInFile.unloadDotsInFile(FILE_NAME_HOME, dotsOfPointsPersentHome);
		unloaderInFile.unloadDotsInFile(FILE_NAME_GUEST, dotsOfPointsPersentGuest);
				
		System.out.println("DONE");
	}

}
