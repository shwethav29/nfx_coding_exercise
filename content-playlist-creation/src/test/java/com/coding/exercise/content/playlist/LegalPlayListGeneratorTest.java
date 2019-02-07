package com.coding.exercise.content.playlist;

import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.exercise.content.playlist.creation.json.service.JsonParserGSONObjectModelImpl;
import com.coding.exercise.content.playlist.creation.model.PlayListData;

public class LegalPlayListGeneratorTest {

	private static PlayListDataParserJSON playListParser;

	@BeforeClass
	public static void initializeService() {
		playListParser = new PlayListDataParserJSON();
		playListParser.setJsonParserService(new JsonParserGSONObjectModelImpl());
	}

	@Test
	public void testLegalPlayListOnePlaylist() {
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
		PlayListData playListData = playListParser.parsePlayListData(reader);
		LegalPlayListPlayer legalPlayListsForUS = new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData,
				"MI3", "US");
		assertTrue("[{V4,V1},{V6,V3}]".equals(legalPlayListsForUS.toString()));
		LegalPlayListPlayer legalPlayListsForCA = new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData,
				"MI3", "CA");
		assertTrue("[{V5,V2}]".equals(legalPlayListsForCA.toString()));
		try {
			new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData, "MI3", "UK");
		} catch (NoLegalPlayListException e) {
			assertTrue(
					"No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the UK"
							.equals(e.getMessage()));
		}

	}

	@Test
	public void testNoLegalPlayListMissingPreroll() {
		StringReader reader = new StringReader("{\n" + "\n" + "  \"content\": [\n" + "\n" + "    {\n" + "\n"
				+ "      \"name\": \"MI3\",\n" + "\n"
				+ "      \"preroll\": [{ \"name\": \"WB1\"},{ \"name\": \"WB2\"}],\n" + "\n" + "      \"videos\": [\n"
				+ "\n"
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
		PlayListData playListData = playListParser.parsePlayListData(reader);
		try {
			new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData, "MI3", "US");
		} catch (NoLegalPlayListException e) {
			assertTrue(
					"No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the US"
							.equals(e.getMessage()));
		}

	}

	@Test
	public void testLegalPlayListthreePreroll() {
		StringReader reader = new StringReader("{\n" + " \"content\": [\n" + "   {\n" + "     \"name\": \"MI3\",\n"
				+ "     \"preroll\": [{ \"name\": \"WB1\"},{ \"name\": \"WB2\"},{ \"name\": \"WB3\"}],\n"
				+ "     \"videos\": [\n"
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
				+ "     ]\n" + "   },\n" + "{\n" + "     \"name\": \"WB3\",\n" + "     \"videos\": [\n"
				+ "       { \"name\": \"V10\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V11\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V12\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   }\n" + " \n" + " ]\n" + "}\n" + "");
		PlayListData playListData = playListParser.parsePlayListData(reader);
		LegalPlayListPlayer legalPlayListsForUS = new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData,
				"MI3", "US");
		System.out.println(legalPlayListsForUS.toString());
		assertTrue("[{V4,V7,V10,V1},{V6,V9,V12,V3}]".equals(legalPlayListsForUS.toString()));
	}

	@Test
	public void testLegalPlayListNoPrerollWB2matchforCA() {
		StringReader reader = new StringReader("{\n" + " \"content\": [\n" + "   {\n" + "     \"name\": \"MI3\",\n"
				+ "     \"preroll\": [{ \"name\": \"WB1\"},{ \"name\": \"WB2\"},{ \"name\": \"WB3\"}],\n"
				+ "     \"videos\": [\n"
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
				+ "       { \"name\": \"V9\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   },\n" + "{\n" + "     \"name\": \"WB3\",\n" + "     \"videos\": [\n"
				+ "       { \"name\": \"V10\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V11\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V12\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   }\n" + " \n" + " ]\n" + "}\n" + "");
		PlayListData playListData = playListParser.parsePlayListData(reader);
		try {
			new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData, "MI3", "CA");
		} catch (NoLegalPlayListException e) {
			assertTrue(
					"No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the CA"
							.equals(e.getMessage()));
		}

	}

	@Test
	public void testLegalPlayListMissingPreroll() {
		StringReader reader = new StringReader("{\n" + " \"content\": [\n" + "   {\n" + "     \"name\": \"MI3\",\n"
				+ "     \"preroll\": [{ \"name\": \"WB1\"},{ \"name\": \"WB2\"},{ \"name\": \"WB3\"}],\n"
				+ "     \"videos\": [\n"
				+ "       { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   },\n" + "   {\n" + "     \"name\": \"Bright\",\n"
				+ "     \"preroll\": [{ \"name\": \"WB1\"},{ \"name\": \"WB2\"}],\n" + "     \"videos\": [\n"
				+ "       { \"name\": \"V1\", \"attributes\": {\"countries\": [\"US\", \"UK\", \"CA\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V2\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V3\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   }\n" + " ],\n" + " \"preroll\": [\n" + "{\n" + "     \"name\": \"WB2\",\n"
				+ "     \"videos\": [\n"
				+ "       { \"name\": \"V7\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V9\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   },\n" + "{\n" + "     \"name\": \"WB3\",\n" + "     \"videos\": [\n"
				+ "       { \"name\": \"V10\", \"attributes\": {\"countries\": [\"US\"], \"language\":\"English\"} },\n"
				+ "       { \"name\": \"V11\", \"attributes\": {\"countries\": [\"CA\"], \"language\": \"French\"} },\n"
				+ "       { \"name\": \"V12\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"Spanish\"} }\n"
				+ "     ]\n" + "   }\n" + " \n" + " ]\n" + "}\n" + "");
		PlayListData playListData = playListParser.parsePlayListData(reader);
		try {
			new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData, "MI3", "US");
		} catch (NoLegalPlayListException e) {
			assertTrue(
					"No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the US"
							.equals(e.getMessage()));
		}

	}

	@Test
	public void testLegalPlayListtooManyPreroll() {
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
				+ "        { \"name\": \"V6\", \"attributes\": {\"countries\": [\"US\"], \"language\": \"English\"} }\n"
				+ "\n" + "      ]\n" + "\n" + "    }\n" + "\n" + "  ]\n" + "\n" + "}");
		PlayListData playListData = playListParser.parsePlayListData(reader);
		try {
			new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData, "MI3", "US");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect preroll data".equals(e.getMessage()));
		}

	}

	@Test
	public void testLegalPlayListZeroContent() {
		StringReader reader = new StringReader("{\n" + "\n" + "  \"content\": [\n" + "\n" + "    {\n" + "\n"
				+ "      \"name\": \"Bright\",\n" + "\n" + "      \"preroll\": [{ \"name\": \"WB1\"}],\n" + "\n"
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
		PlayListData playListData = playListParser.parsePlayListData(reader);
		try {
			new LegalPlayListGeneratorImpl().getLegalPlaylists(playListData, "MI3", "US");
		} catch (IllegalArgumentException e) {
			assertTrue("Zero or too many Contents for MI3".equals(e.getMessage()));
		}

	}

}
