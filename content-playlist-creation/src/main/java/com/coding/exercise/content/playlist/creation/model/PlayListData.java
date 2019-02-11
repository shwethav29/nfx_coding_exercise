package com.coding.exercise.content.playlist.creation.model;

import java.util.ArrayList;
import java.util.List;

public class PlayListData {

	private List<Content> content = new ArrayList<Content>();
	private List<Preroll> preroll = new ArrayList<Preroll>();
	public List<Content> getContent() {
		return content;
	}
	public void setContent(List<Content> content) {
		this.content = content;
	}
	public List<Preroll> getPreroll() {
		return preroll;
	}
	public void setPreroll(List<Preroll> preroll) {
		this.preroll = preroll;
	}
	
}
