package ua.nenya.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;

public class UnloaderInFile {

	public void unloadDotsOfWinsInFile(String fileName, int beginOfInterval, int endOfInterval, List<Dot> dots,
			String result) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(";");
			for (int i = beginOfInterval; i <= endOfInterval; i++) {
				writer.write(i + ";");
			}
			writer.newLine();
			int number = 0;
			for (int i = beginOfInterval; i <= endOfInterval; i++) {
				writer.write(i + ";");
				for (int j = beginOfInterval; j <= endOfInterval; j++) {
					Map<String,List<Game>> resultMap = dots.get(number).getResult();
					for(Map.Entry<String, List<Game>> pair: resultMap.entrySet()){
						if(pair.getKey().equals(result)){
							writer.write(pair.getValue().size() + ";");
						}
					}
					number++;
				}
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
