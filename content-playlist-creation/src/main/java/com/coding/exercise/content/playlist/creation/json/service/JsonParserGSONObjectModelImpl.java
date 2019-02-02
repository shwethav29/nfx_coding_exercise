package com.coding.exercise.content.playlist.creation.json.service;

import java.io.Reader;
import java.io.StringReader;

import com.coding.exercise.content.playlist.creation.model.Content;
import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.coding.exercise.content.playlist.creation.model.Preroll;
import com.coding.exercise.content.playlist.filter.PlayListFilterByContentIdCriteria;
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
	
	public static void main(String[] args) {
		JsonParserGSONObjectModelImpl parserImpl = new JsonParserGSONObjectModelImpl();
		StringReader reader = new StringReader("{\n" + 
				" \"content\": [\n" + 
				"   {\n" + 
				"     \"name\": \"MI3\",\n" + 
				"     \"preroll\": [{ \"name\": \"WB1\"}],\n" + 
				"     \"videos\": [\n" + 
				"       { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n" + 
				"       { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n" + 
				"       { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n" + 
				"     ]\n" + 
				"   },\n" + 
				"   {\n" + 
				"     \"name\": \"Bright\",\n" + 
				"     \"preroll\": [{ \"name\": \"WB1\"},{ \"name\": \"WB2\"}],\n" + 
				"     \"videos\": [\n" + 
				"       { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n" + 
				"       { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n" + 
				"       { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n" + 
				"     ]\n" + 
				"   }\n" + 
				" ],\n" + 
				" \"preroll\": [\n" + 
				"   {\n" + 
				"     \"name\": \"WB1\",\n" + 
				"     \"videos\": [\n" + 
				"       { \"name\": \"V4\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n" + 
				"       { \"name\": \"V5\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n" + 
				"       { \"name\": \"V6\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n" + 
				"     ]\n" + 
				"   },\n" + 
				"{\n" + 
				"     \"name\": \"WB2\",\n" + 
				"     \"videos\": [\n" + 
				"       { \"name\": \"V7\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n" + 
				"       { \"name\": \"V8\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n" + 
				"       { \"name\": \"V9\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n" + 
				"     ]\n" + 
				"   }\n" + 
				" ]\n" + 
				"}\n" + 
				"");
		
		PlayListData data = parserImpl.parseJson(reader);
		PlayListFilterByContentIdCriteria contentFilter = new PlayListFilterByContentIdCriteria("Bright");
		PlayListData filteredData = contentFilter.applyFilterCriteria(data);
		for(Content content:filteredData.getContent()) {
			System.out.println(content.getName());
		}
		for(Preroll preroll: filteredData.getPreroll()) {
			System.out.println(preroll.getName());
		}
		for(Content content:data.getContent()) {
			System.out.println(content.getName());
		}
	}
}
