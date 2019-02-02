package com.coding.exercise.content.playlist;

import com.coding.exercise.content.playlist.creation.model.PlayListData;
/**
 * Interface for PlayListGenerator
 * 
 * Implementation class should look for the contentIdentifier for the give country code and generate the legal playList
 * 
 * Throws IllgalStateException in case the content Data was not found in playlistdata or found too many content matching the content identifier.
 * throws {@link NoLegalPlayListException} if no legal Playlist could be formed due to no compatible prerolls for the given content identifier and countryCode.
 * @author svasanth
 *
 */
public interface LegalPlayListGenerator {
	LegalPlayListPlayer getLegalPlaylists(PlayListData playListData,String contentIdentifier,String countryCode) ;
}
