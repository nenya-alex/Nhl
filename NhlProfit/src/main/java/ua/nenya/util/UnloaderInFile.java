package ua.nenya.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ua.nenya.domain.BetResult;
import ua.nenya.domain.Profit;

public class UnloaderInFile {

	public void unloadDotsOfWinsInFile(String fileName, String season, int beginOfInterval, int endOfInterval, Profit profit) {
		List<BetResult> betResults = profit.getBetResults();
		try (BufferedWriter winWriter = new BufferedWriter(new FileWriter(fileName+"Win"+season+".csv"));
				BufferedWriter loseWriter = new BufferedWriter(new FileWriter(fileName+"Lose"+season+".csv"))) {
			for(BetResult betResult: betResults){
				if(betResult.isWin()){
					winWriter.write(betResult.getX() + ";"+ betResult.getY());
					winWriter.newLine();
				}else{
					loseWriter.write(betResult.getX() + ";"+ betResult.getY());
					loseWriter.newLine();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
