package com.coding.exercise.content.playlist.creation.model;

import java.util.List;

public class ListPlayListData {

	private List<Content> content;
	private List<Preroll> preroll;
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
