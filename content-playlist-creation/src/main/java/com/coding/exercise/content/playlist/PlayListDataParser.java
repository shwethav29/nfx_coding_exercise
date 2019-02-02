package com.coding.exercise.content.playlist;

import java.io.Reader;

import com.coding.exercise.content.playlist.creation.model.PlayListData;
/**
 * Interface to parse the input PlayListData 
 * returns {@link PlayListData}
 * @author svasanth
 *
 */
interface PlayListDataParser {
	/**
	 * parse the playlistdata from the reader to PlayListData model
	 * @param reader
	 * @return
	 */
	public PlayListData parsePlayListData(Reader reader);
}
