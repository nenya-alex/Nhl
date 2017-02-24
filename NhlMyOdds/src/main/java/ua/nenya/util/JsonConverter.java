package ua.nenya.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;

public class JsonConverter<T>{
	
	public List<Game> initGamesFromJson(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		List<Game> result = new ArrayList<>();
		try {
			result = mapper.readValue(new File(fileName), new TypeReference<List<Game>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Dot> initDotsFromJson(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		List<Dot> result = new ArrayList<>();
		try {
			result = mapper.readValue(new File(fileName), new TypeReference<List<Dot>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void convertToJSON(List<T> list,File file) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.writeValue(file, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void convertToJSON(T object,File file) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.writeValue(file, object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
