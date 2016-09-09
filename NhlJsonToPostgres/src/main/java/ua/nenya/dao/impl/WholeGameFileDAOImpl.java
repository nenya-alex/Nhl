package ua.nenya.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ua.nenya.domain.WholeGame;

//@Repository
public class WholeGameFileDAOImpl implements WholeGameFileDAO {
	
	@Override
	public List<WholeGame> initWholeGamesFromJsonFile(String fileName) {
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

}
