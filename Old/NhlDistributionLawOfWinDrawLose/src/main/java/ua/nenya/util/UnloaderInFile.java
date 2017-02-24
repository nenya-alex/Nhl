package ua.nenya.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Unit;

public class UnloaderInFile {

	public void unloadInFile(String fileName, List<Unit> units){
		try(FileWriter fw = new FileWriter(fileName)) {
			fw.write("A");			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void unloadDotsOfWinsInFile(String fileName, List<Dot> dotsOfWins, String result) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)) ) {
			writer.write("Count;Season;gameId;Result;X;Y");
			writer.newLine();
			for(int i=0; i < dotsOfWins.size(); i++){
				Dot dot = dotsOfWins.get(i);
				if(dot.getResult().equals(result)){
					writer.write(i+";"+dot.getSeason()+";"+dot.getGameId()+";"+dot.getResult()+";"+dot.getX()+";"+dot.getY());
					writer.newLine();
				}
			}					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
