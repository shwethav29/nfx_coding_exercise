package com.coding.exercise.content.playlist.filter;

import java.util.List;

import com.coding.exercise.content.playlist.creation.model.Video;
/**
 * Implements {@link VideoFilterCriteria}
 * This is a filter class to filter the list of {@link Video} by country and language. 
 * see other filters in the Video Filters {@link VideoFilterByCountryCode}, {@link VideoFilterByLanguage}
 * 
 * @author svasanth
 *
 */
 public class VideoFilterByCountryAndLanguage implements VideoFilterCriteria {

	private final String countryCode;
	
	private final String language;
	
	public VideoFilterByCountryAndLanguage(String countryCode, String language) {
		this.countryCode = countryCode;
		this.language = language;
	}

	@Override
	public List<Video> filterVideos(List<Video> videoList) {
		VideoFilterByCountryCode videosByCountryFilter = new VideoFilterByCountryCode(countryCode); 
		VideoFilterByLanguage videoByLanguage = new VideoFilterByLanguage(language);
		return videoByLanguage.filterVideos(videosByCountryFilter.filterVideos(videoList));
	}

}
