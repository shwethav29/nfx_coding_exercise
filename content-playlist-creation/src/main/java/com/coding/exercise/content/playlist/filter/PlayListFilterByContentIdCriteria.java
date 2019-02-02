package com.coding.exercise.content.playlist.filter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.coding.exercise.content.playlist.creation.model.Content;
import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.coding.exercise.content.playlist.creation.model.Preroll;
/**
 * Implements {@link PlayListFilterCriteria}
 * 
 * Filter class to filter the PlayLists based on the content name.
 * 
 * The result playlist will consist of Playlists with the {@link Content} matching the contentIdentifier and the {@link Preroll} associated with this content
 * 
 * @author svasanth
 *
 */
public final class PlayListFilterByContentIdCriteria implements PlayListFilterCriteria{

	private final String contentIdentifier;
	
	public PlayListFilterByContentIdCriteria(String contentIdentifier) {
		this.contentIdentifier=contentIdentifier;
	}

	@Override
	public PlayListData applyFilterCriteria(PlayListData playListData) {
		PlayListData filteredPlayListData = new PlayListData();
		Content[] filteredContentArray = Stream.of(playListData.getContent()).filter(c -> c.getName().equals(contentIdentifier)).toArray(Content[]::new);
		if(filteredContentArray.length>0) {
			Set<String> collect =Stream.of(filteredContentArray[0].getPreroll()).flatMap(p -> Stream.of(p.getName())).collect(Collectors.toSet());
			Preroll[] prerollsForMatchingContent = Stream.of(playListData.getPreroll()).filter(p -> collect.contains(p.getName())).toArray(Preroll[]::new);
			filteredPlayListData.setContent(filteredContentArray);
			filteredPlayListData.setPreroll(prerollsForMatchingContent);
		}
		return filteredPlayListData;
	}
	
	
	
}
