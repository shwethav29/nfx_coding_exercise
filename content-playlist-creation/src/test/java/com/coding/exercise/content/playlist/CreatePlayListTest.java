package com.coding.exercise.content.playlist;

import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import org.junit.Test;

import com.coding.exercise.content.playlist.creation.model.PlayListData;

public class CreatePlayListTest {

	@Test
	public void testPlayListParser() {
		StringReader reader = new StringReader("{\n" + "\n" + "  \"content\": [\n" + "\n" + "    {\n" + "\n"
				+ "      \"name\": \"MI3\",\n" + "\n" + "      \"preroll\": [{ \"name\": \"WB1\"}],\n" + "\n"
				+ "      \"videos\": [\n" + "\n"
				+ "        { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "\n" + "      ]\n" + "\n" + "    }\n" + "\n" + "  ],\n" + "\n" + "  \"preroll\": [\n" + "\n"
				+ "    {\n" + "\n" + "      \"name\": \"WB1\",\n" + "\n" + "      \"videos\": [\n" + "\n"
				+ "        { \"name\": \"V4\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V5\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V6\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "\n" + "      ]\n" + "\n" + "    }\n" + "\n" + "  ]\n" + "\n" + "}");
		CreatePlayList createPlayList = new CreatePlayList();
		PlayListData playListData = createPlayList.loadPlayListData(reader, DataFormat.JSON);
		LegalPlayListPlayer legalPlayListsForUS = createPlayList.getLegalPlayListPlayer(playListData, "MI3", "US");
		assertTrue("[{V4,V1},{V6,V3}]".equals(legalPlayListsForUS.toString()));
		LegalPlayListPlayer legalPlayListsForCA = createPlayList.getLegalPlayListPlayer(playListData, "MI3", "CA");
		assertTrue("[{V5,V2}]".equals(legalPlayListsForCA.toString()));
		try {
			createPlayList.getLegalPlayListPlayer(playListData, "MI3", "UK");
		} catch (NoLegalPlayListException e) {
			assertTrue(
					"No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the UK"
							.equals(e.getMessage()));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlayListParserXML() {
		StringReader reader = new StringReader("{\n" + "\n" + "  \"content\": [\n" + "\n" + "    {\n" + "\n"
				+ "      \"name\": \"MI3\",\n" + "\n" + "      \"preroll\": [{ \"name\": \"WB1\"}],\n" + "\n"
				+ "      \"videos\": [\n" + "\n"
				+ "        { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "\n" + "      ]\n" + "\n" + "    }\n" + "\n" + "  ],\n" + "\n" + "  \"preroll\": [\n" + "\n"
				+ "    {\n" + "\n" + "      \"name\": \"WB1\",\n" + "\n" + "      \"videos\": [\n" + "\n"
				+ "        { \"name\": \"V4\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V5\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "\n"
				+ "        { \"name\": \"V6\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "\n" + "      ]\n" + "\n" + "    }\n" + "\n" + "  ]\n" + "\n" + "}");
		CreatePlayList createPlayList = new CreatePlayList();
		createPlayList.loadPlayListData(reader, DataFormat.XML);
	}

}
