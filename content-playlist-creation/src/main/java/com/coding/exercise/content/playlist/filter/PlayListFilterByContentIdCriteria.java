package com.coding.exercise.content.playlist.filter;

import java.util.List;
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
		List<Content> filteredContentList = playListData.getContent().stream().filter(c -> c.getName().equals(contentIdentifier)).collect(Collectors.toList());
		if(filteredContentList.size()>0) {
			Set<String> collect =filteredContentList.get(0).getPreroll().stream().flatMap(p -> Stream.of(p.getName())).collect(Collectors.toSet());
			List<Preroll> prerollsForMatchingContent = playListData.getPreroll().stream().filter(p -> collect.contains(p.getName())).collect(Collectors.toList());
			filteredPlayListData.setContent(filteredContentList);
			filteredPlayListData.setPreroll(prerollsForMatchingContent);
		}
		return filteredPlayListData;
	}
	
	
	
}
