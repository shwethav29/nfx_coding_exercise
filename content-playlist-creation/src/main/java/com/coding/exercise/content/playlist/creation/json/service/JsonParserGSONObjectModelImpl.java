package com.coding.exercise.content.playlist.creation.json.service;

import java.io.Reader;

import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Implementation of {@link JsonParserService} using the GSON parser to parse PlayListData 
 * from the JSON file and load to {@link PlayListData} 
 * As this implementation uses ObjectModeling for parsing and loading the data it is not suitable for very Big JSON files as it loads 
 * entire file to memory.
 * 
 * @author svasanth
 *
 */
public final class JsonParserGSONObjectModelImpl implements JsonParserService{
	
	public  PlayListData parseJson(Reader inputReader) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		PlayListData playListData = gson.fromJson(inputReader, PlayListData.class);
		return playListData;
	}
	
}
