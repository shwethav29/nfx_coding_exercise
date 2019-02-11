package com.coding.exercise.content.playlist.creation.json.service;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.coding.exercise.content.playlist.creation.model.Content;
import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.coding.exercise.content.playlist.creation.model.Preroll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class JsonParserGSONStream {

	
	public PlayListData parseJson(Reader reader,String contentIdentifier) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		PlayListData playListData = new PlayListData();
		Set<String> prerollsToLookFor = new HashSet<String>();
		try (JsonReader jsonReader = new JsonReader(reader);){
			jsonReader.beginObject();
			while(jsonReader.hasNext()) {
				String name = jsonReader.nextName();
				
				if(("content").equals(name)) {
					jsonReader.beginArray();
					while(jsonReader.hasNext()) {
						Content content = gson.fromJson(jsonReader, Content.class);
						if(contentIdentifier.contentEquals(content.getName())) {
							playListData.getContent().add(content);
							prerollsToLookFor = content.getPreroll().stream().map(preroll -> preroll.getName()).collect(Collectors.toSet());
						}
					}
					jsonReader.endArray();
				}
				if(("preroll").equals(name)) {
					jsonReader.beginArray();
					while(jsonReader.hasNext()) {
						Preroll preroll = gson.fromJson(jsonReader, Preroll.class);
						if(prerollsToLookFor.contains(preroll.getName()))
							playListData.getPreroll().add(preroll);
					}
					jsonReader.endArray();
				}
			}
			jsonReader.endObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playListData;
	}

	public static void main(String args[]) {
		JsonParserGSONStream jsonStreamParser = new JsonParserGSONStream();
		StringReader reader = new StringReader("{\n" + " \"content\": [\n" + "   {\n" + "     \"name\": \"MI3\",\n"
				+ "     \"preroll\": [{ \"name\": \"WB1\"}],\n" + "     \"videos\": [\n"
				+ "       { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   },\n" + "   {\n" + "     \"name\": \"Bright\",\n"
				+ "     \"preroll\": [{ \"name\": \"WB1\"},{ \"name\": \"WB2\"}],\n" + "     \"videos\": [\n"
				+ "       { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   }\n" + " ],\n" + " \"preroll\": [\n" + "   {\n" + "     \"name\": \"WB1\",\n"
				+ "     \"videos\": [\n"
				+ "       { \"name\": \"V4\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V5\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V6\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   },\n" + "{\n" + "     \"name\": \"WB2\",\n" + "     \"videos\": [\n"
				+ "       { \"name\": \"V7\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V8\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V9\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   }\n" + " ]\n" + "}\n" + "");
		PlayListData playlistData = jsonStreamParser.parseJson(reader,"MI3");
	}
}
