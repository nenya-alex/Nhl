package ua.nenya.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ua.nenya.domain.WholeGame;

public class JsonConverter<T>{
	
	public List<T> initFromJson(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> result = new ArrayList<>();
		try {
			result = mapper.readValue(new File(fileName), new TypeReference<List<T>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<WholeGame> initWholeGamesFromJson(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		List<WholeGame> result = new ArrayList<>();
		try {
			result = mapper.readValue(new File(fileName), new TypeReference<List<WholeGame>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void convertToJSON(List<T> result, File file) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.writeValue(file, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
