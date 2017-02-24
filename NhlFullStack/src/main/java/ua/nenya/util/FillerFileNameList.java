package ua.nenya.util;

import java.util.ArrayList;
import java.util.List;

public class FillerFileNameList {
	
	private static final String SEASON_15_JSON = "src/main/resources/nhl15.json";
	private static final String SEASON_14_JSON = "src/main/resources/nhl14.json";
	private static final String SEASON_13_JSON = "src/main/resources/nhl13.json";
	private static final String SEASON_12_JSON = "src/main/resources/nhl12.json";
	private static final String SEASON_11_JSON = "src/main/resources/nhl11.json";
	private static final String SEASON_10_JSON = "src/main/resources/nhl10.json";
	private static final String SEASON_09_JSON = "src/main/resources/nhl09.json";
	private static final String SEASON_08_JSON = "src/main/resources/nhl08.json";
	private static final String SEASON_07_JSON = "src/main/resources/nhl07.json";
	private static final String SEASON_06_JSON = "src/main/resources/nhl06.json";
	private static final String SEASON_05_JSON = "src/main/resources/nhl05.json";

	public List<String> fillFileListForStatistics(){
		
		List<String> files = new ArrayList<>();
		files.add(SEASON_05_JSON);
		files.add(SEASON_06_JSON);
		files.add(SEASON_07_JSON);
		files.add(SEASON_08_JSON);
		files.add(SEASON_09_JSON);
		files.add(SEASON_10_JSON);
		files.add(SEASON_11_JSON);
		files.add(SEASON_12_JSON);
		files.add(SEASON_13_JSON);
		files.add(SEASON_14_JSON);
//		files.add(SEASON_15_JSON);
		
		return files;		
	}

	public List<String> fillFileListForBetting() {
		
		List<String> files = new ArrayList<>();
//		files.add(SEASON_05_JSON);
//		files.add(SEASON_06_JSON);
//		files.add(SEASON_07_JSON);
//		files.add(SEASON_08_JSON);
//		files.add(SEASON_09_JSON);
//		files.add(SEASON_10_JSON);
//		files.add(SEASON_11_JSON);
//		files.add(SEASON_13_JSON);
//		files.add(SEASON_14_JSON);
		files.add(SEASON_15_JSON);
		
		return files;	
	}	
}
