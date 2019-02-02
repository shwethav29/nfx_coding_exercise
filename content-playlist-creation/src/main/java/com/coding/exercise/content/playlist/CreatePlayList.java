package com.coding.exercise.content.playlist;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.google.common.base.Strings;
/**
 * This class exposes methods to parsePlayList data and to get the playlists from the playListData.
 * Uses Factory to get the parser for parsing the incoming data based on the dataformat. 
 * @author svasanth
 *
 */
public final class CreatePlayList {
	
	public static final Logger logger = LogManager.getLogger(CreatePlayList.class);
	/**
	 * Return the {@link PlayListData} by parsing the data from the reader using the appropriate parser based on the {@link DataFormat}
	 */

	public PlayListData loadPlayListData(Reader reader,DataFormat dataFormat) {
		checkNotNull(reader, "Reader was null");
		checkNotNull(dataFormat,"Data format null");
		PlayListDataParserFactory playListDataParserFactory = new PlayListDataParserFactory();
		PlayListDataParser parser = playListDataParserFactory.createPlayListDataParser(dataFormat);
		logger.debug("Play list data was parser from the input");
		return parser.parsePlayListData(reader);
	}
	/**
	 * Returns {@link LegalPlayListPlayer} which consist of a list of playlists. 
	 * A legal sequence of pre-rolls and content videos in correct order as instructions to a player. 
	 * the playList will look for the contentIdentifier and returns the possible legal playlists 
	 * for the given country.
	 */
	public LegalPlayListPlayer getLegalPlayListPlayer(PlayListData playListData,String contentIdentifier,String countryCode) {
		checkNotNull(playListData,"No PlayList Data Found");
		checkArgument(!Strings.isNullOrEmpty(contentIdentifier),"Missing Content Identifier not specified");
		checkArgument(!Strings.isNullOrEmpty(countryCode),"Missing Country code");
		LegalPlayListGenerator legalListGenerator = new LegalPlayListGeneratorImpl();
		return legalListGenerator.getLegalPlaylists(playListData, contentIdentifier, countryCode);
	}
}
