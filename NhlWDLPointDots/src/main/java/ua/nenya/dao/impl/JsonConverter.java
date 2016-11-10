package ua.nenya.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ua.nenya.domain.Dot;
import ua.nenya.domain.WholeGame;

public class JsonConverter{
	
	public List<WholeGame> initWholeGamesFromJson(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		List<WholeGame> wholeGames = new ArrayList<>();
		try {
			wholeGames = mapper.readValue(new File(fileName), new TypeReference<List<WholeGame>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wholeGames;
	}

	public void convertDotsToJSON(List<Dot> dots,File file) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.writeValue(file, dots);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
