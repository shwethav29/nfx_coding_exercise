package com.coding.exercise.content.playlist.filter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.coding.exercise.content.playlist.creation.model.Video;
/**
 * Implements {@link VideoFilterCriteria}
 * Filter class that returns the list of {@link Video} that matches the countryCode with the country attribute of the video 
 * @author svasanth
 *
 */
public final class VideoFilterByCountryCode implements VideoFilterCriteria {

	private final String countryCode;
	
	public VideoFilterByCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public List<Video> filterVideos(List<Video> videoList) {
		List<Video> contentVideoList= videoList.stream().filter(v -> Stream.of(v.getAttributes().getCountries()).collect(Collectors.toSet()).contains(countryCode)).collect(Collectors.toList());
		return contentVideoList;
	}

}
