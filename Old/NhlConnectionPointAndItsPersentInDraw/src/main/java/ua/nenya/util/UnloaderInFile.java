package ua.nenya.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ua.nenya.domain.Dot;

public class UnloaderInFile {

	public void unloadDotsInFile(String fileName, List<Dot> dots) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)) ) {
			writer.write("Count;Season;gameId;Points;Persents");
			writer.newLine();
			for(int i=0; i < dots.size(); i++){
				Dot dot = dots.get(i);
				writer.write(i+";"+dot.getSeason()+";"+dot.getGameId()+";"+dot.getX()+";"+dot.getY());
				writer.newLine();
				
			}					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
