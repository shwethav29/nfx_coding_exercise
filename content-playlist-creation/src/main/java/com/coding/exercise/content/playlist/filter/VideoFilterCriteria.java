package com.coding.exercise.content.playlist.filter;

import java.util.List;

import com.coding.exercise.content.playlist.creation.model.Video;
/**
 * Filter interface to filter the {@link Video}
 * see Filter implementations like {@link VideoFilterByCountryAndLanguage} {@link VideoFilterByCountryCode}
 * @author svasanth
 *
 */
public interface VideoFilterCriteria {
	/**
	 * Returns the filtered list of videoLists matching the criteria implemented by the concrete class
	 * @param videoList
	 * @return
	 */
	List<Video> filterVideos(List<Video> videoList);
}
