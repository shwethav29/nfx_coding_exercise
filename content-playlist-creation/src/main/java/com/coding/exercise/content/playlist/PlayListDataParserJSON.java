package com.coding.exercise.content.playlist;

import java.io.Reader;

import com.coding.exercise.content.playlist.creation.json.service.JsonParserService;
import com.coding.exercise.content.playlist.creation.model.PlayListData;
/**
 * Implements {@link PlayListDataParser} 
 * This parser parse data in JSON to {@link PlayListData}
 * @author svasanth
 *
 */
public final class PlayListDataParserJSON implements PlayListDataParser {

	public PlayListDataParserJSON() {
		super();
	}
	public PlayListDataParserJSON(JsonParserService jsonParserService) {
		this.jsonParserService = jsonParserService;
	}

	private JsonParserService jsonParserService;
	
	
	public JsonParserService getJsonParserService() {
		return jsonParserService;
	}


	public void setJsonParserService(JsonParserService jsonParserService) {
		this.jsonParserService = jsonParserService;
	}

	@Override
	public PlayListData parsePlayListData(Reader reader) {
		return jsonParserService.parseJson(reader);
	}

}
