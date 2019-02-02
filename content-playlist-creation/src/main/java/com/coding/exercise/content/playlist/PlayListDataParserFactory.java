package com.coding.exercise.content.playlist;

import com.coding.exercise.content.playlist.creation.json.service.JsonParserGSONObjectModelImpl;

/**
 * This is the Factory for creating the parser. Based on the input data format.
 * Default uses JSON parser and default implementation uses GSON for Parsing the data.
 *
 * @author svasanth
 *
 */
public class PlayListDataParserFactory {
	public PlayListDataParser createPlayListDataParser(DataFormat parserType) {
		
		PlayListDataParserJSON jsonParser = new PlayListDataParserJSON(new JsonParserGSONObjectModelImpl());

		if(DataFormat.JSON.equals(parserType)) {
			return jsonParser ;
		}
		if(DataFormat.XML.equals(parserType)) {
			throw new IllegalArgumentException("Currently XML format is not supported");
		}
		return jsonParser;
	}
}
