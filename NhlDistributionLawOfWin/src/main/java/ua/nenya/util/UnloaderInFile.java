package ua.nenya.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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

	public void unloadDotsOfWinsInFile(String unloadingDotsFileName, List<Dot> dotsOfWins) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(unloadingDotsFileName));
			BufferedReader reader = new BufferedReader(new FileReader(unloadingDotsFileName)) ) {
			writer.write("Count;Season;gameId;X;Y");
			writer.newLine();
			for(int i=0; i < dotsOfWins.size(); i++){
				Dot dot = dotsOfWins.get(i);
				writer.write(i+";"+dot.getSeason()+";"+dot.getGameId()+";"+dot.getX()+";"+dot.getY());
				writer.newLine();
			}					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
