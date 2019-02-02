package com.coding.exercise.content.playlist.filter;

import com.coding.exercise.content.playlist.creation.model.PlayListData;
/**
 * Filter interface to filter the {@link PlayListData}.
 * 
 * @author svasanth
 *
 */
public interface PlayListFilterCriteria {

	PlayListData applyFilterCriteria(PlayListData playListData);
}
