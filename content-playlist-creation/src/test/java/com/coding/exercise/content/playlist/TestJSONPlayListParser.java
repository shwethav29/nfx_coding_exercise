package com.coding.exercise.content.playlist;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.exercise.content.playlist.creation.json.service.JsonParserGSONObjectModelImpl;
import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.google.gson.JsonSyntaxException;

public class TestJSONPlayListParser{
	private static PlayListDataParserJSON playListParser;
	
	@BeforeClass
	public static void initializeService() {
		playListParser = new PlayListDataParserJSON();
		playListParser.setJsonParserService(new JsonParserGSONObjectModelImpl());
	}
	
	@Test
	public void testValidJSONParserFromStringReader() {
		playListParser = new PlayListDataParserJSON();
		playListParser.setJsonParserService(new JsonParserGSONObjectModelImpl());
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

		PlayListData playListData = playListParser.parsePlayListData(reader);
		
		assertTrue(playListData.getContent().length>0);
		assertTrue(playListData.getPreroll().length>0);
	}
	
	@Test
	public void testEmptyJSON() {
		StringReader reader = new StringReader("");
		PlayListData playListData = playListParser.parsePlayListData(reader);
		assertNull(playListData);
	}
	
	@Test(expected= JsonSyntaxException.class)
	public void testInvalidJson() {
		StringReader reader = new StringReader("[    \"test\" : 123]");
		playListParser.parsePlayListData(reader);
	}
	
	@Test(expected= JsonSyntaxException.class)
	public void testUnexpectedJsonContent(){
		StringReader reader = new StringReader("{\n" + 
				" \"content\": \n" + 
				"   {\n" + 
				"     \"name\": \"MI3\",\n" + 
				"     \"preroll\": [{ \"name\": \"WB1\"}],\n" + 
				"     \"videos\": [\n" + 
				"       { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n" + 
				"       { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n" + 
				"       { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n" + 
				"     ]\n" + 
				"   }\n" + 
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
		playListParser.parsePlayListData(reader);
	}
	
	@Test
	public void testParseJSONFromFile() throws IOException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource("test.json").getFile());
		System.out.println(file.getAbsolutePath());		
		try (Reader reader = new FileReader(file)){
			PlayListData playListData = playListParser.parsePlayListData(reader);
			assertTrue(playListData.getContent().length==1);
			assertTrue(playListData.getPreroll().length==1);
		} 
	}
}
