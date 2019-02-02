package com.coding.exercise.content.playlist;

import java.io.Reader;

import com.coding.exercise.content.playlist.creation.model.PlayListData;
/**
 * This class exposes methods to parsePlayList data and to get the playlists from the playListData.
 * Uses Factory to get the parser for parsing the incoming data based on the dataformat. 
 * @author svasanth
 *
 */
public final class CreatePlayList {
	
	/**
	 * Return the {@link PlayListData} by parsing the data from the reader using the appropriate parser based on the {@link DataFormat}
	 */
	public PlayListData loadPlayListData(Reader reader,DataFormat dataFormat) {
		PlayListDataParserFactory playListDataParserFactory = new PlayListDataParserFactory();
		PlayListDataParser parser = playListDataParserFactory.createPlayListDataParser(dataFormat);
		return parser.parsePlayListData(reader);
	}
	/**
	 * Returns {@link LegalPlayListPlayer} which consist of a list of playlists. 
	 * A legal sequence of pre-rolls and content videos in correct order as instructions to a player. 
	 * the playList will look for the contentIdentifier and returns the possible legal playlists 
	 * for the given country.
	 */
	public LegalPlayListPlayer getLegalPlayListPlayer(PlayListData playListData,String contentIdentifier,String countryCode) {
		LegalPlayListGenerator legalListGenerator = new LegalPlayListGeneratorImpl();
		return legalListGenerator.getLegalPlaylists(playListData, contentIdentifier, countryCode);
	}
}
