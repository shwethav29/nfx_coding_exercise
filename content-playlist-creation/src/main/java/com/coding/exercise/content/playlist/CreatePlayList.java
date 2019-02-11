package com.coding.exercise.content.playlist;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.exercise.content.playlist.LegalPlayListPlayer.LegalPlayList;
import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.google.common.base.Strings;

/**
 * This class exposes methods to parsePlayList data and to get the playlists
 * from the playListData. Uses Factory to get the parser for parsing the
 * incoming data based on the dataformat.
 * 
 * @author svasanth
 *
 */
public final class CreatePlayList {

	public static final Logger logger = LogManager.getLogger(CreatePlayList.class);

	/**
	 * Return the {@link PlayListData} by parsing the data from the reader using the
	 * appropriate parser based on the {@link DataFormat}
	 */

	public PlayListData loadPlayListData(Reader reader, DataFormat dataFormat) {
		checkNotNull(reader, "Reader was null");
		checkNotNull(dataFormat, "Data format null");
		PlayListDataParserFactory playListDataParserFactory = new PlayListDataParserFactory();
		PlayListDataParser parser = playListDataParserFactory.createPlayListDataParser(dataFormat);
		logger.debug("Play list data was parser from the input");
		return parser.parsePlayListData(reader);
	}

	/**
	 * Returns {@link LegalPlayListPlayer} which consist of a list of playlists. A
	 * legal sequence of pre-rolls and content videos in correct order as
	 * instructions to a player. the playList will look for the contentIdentifier
	 * and returns the possible legal playlists for the given country.
	 */
	public LegalPlayListPlayer getLegalPlayListPlayer(PlayListData playListData, String contentIdentifier,
			String countryCode) {
		checkNotNull(playListData, "No PlayList Data Found");
		checkArgument(!Strings.isNullOrEmpty(contentIdentifier), "Missing Content Identifier not specified");
		checkArgument(!Strings.isNullOrEmpty(countryCode), "Missing Country code");
		LegalPlayListGenerator legalListGenerator = new LegalPlayListGeneratorImpl();
		return legalListGenerator.getLegalPlaylists(playListData, contentIdentifier, countryCode);
	}

	public static void printLegalPlayLists(LegalPlayListPlayer legalPlayListPlayer) {
		int i = 0;
		for (LegalPlayList legalPlayList : legalPlayListPlayer.getListOfPlayLists()) {
			System.out.println("PlayList" + ++i);
			System.out.println(legalPlayList.toString());
		}

	}

	public static void main(String[] args) throws IOException {
		// read name of the file
		System.out.println("Location of JSON file:");
		try (Scanner sc = new Scanner(System.in)) {
			String jsonFilePath = sc.nextLine();
			checkArgument(!Strings.isNullOrEmpty(jsonFilePath), "JSON file required");
			File jsonFile = new File(jsonFilePath);
			checkArgument(jsonFile.exists() && jsonFile.isFile(), "File not found");

			// read content Identifier
			System.out.println("Content Identifier:");
			String contentIdentifier = sc.nextLine();

			// read country code
			System.out.println("Country Code:");
			String countryCode = sc.nextLine();

			try (Reader reader = new FileReader(jsonFile)) {
				// parse and load playlist data
				CreatePlayList createPlayList = new CreatePlayList();
				PlayListData playListData = createPlayList.loadPlayListData(reader, DataFormat.JSON);

				// look for legal playlists for the content identifier and the country code

				try {
					LegalPlayListPlayer legalPlayListPlayer = createPlayList.getLegalPlayListPlayer(playListData,
							contentIdentifier, countryCode);
					printLegalPlayLists(legalPlayListPlayer);
				} catch (NoLegalPlayListException noLegalPlaylistException) {
					System.out.println(noLegalPlaylistException.getMessage());
				}

			}
		}

	}
}
