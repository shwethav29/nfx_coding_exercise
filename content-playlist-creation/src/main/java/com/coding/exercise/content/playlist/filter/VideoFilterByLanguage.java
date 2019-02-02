package com.coding.exercise.content.playlist.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.coding.exercise.content.playlist.creation.model.Video;
/**
 * Implements {@link VideoFilterCriteria}
 * This is a filter class to filter the list of {@link Video} by language. 
 * see other filters in the Video Filters {@link VideoFilterByCountryCode}, {@link VideoFilterByCountryAndLanguage}
 * 
 * @author svasanth
 *
 */
public final class VideoFilterByLanguage implements VideoFilterCriteria {

	private final String language;
	
	
	public VideoFilterByLanguage(String language) {
		super();
		this.language = language;
	}


	public String getLanguage() {
		return language;
	}

	@Override
	public List<Video> filterVideos(List<Video> videoList) {
		List<Video> contentVideoList= videoList.stream().filter(v -> v.getAttributes().getLanguage().equals(language)).collect(Collectors.toList());
		return contentVideoList;
	}

}
