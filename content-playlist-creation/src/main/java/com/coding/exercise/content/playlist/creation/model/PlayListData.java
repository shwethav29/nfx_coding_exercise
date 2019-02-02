package com.coding.exercise.content.playlist.creation.model;

public class PlayListData {

	private Content[] content;
	private Preroll[] preroll;
	public Content[] getContent() {
		return content;
	}
	public void setContent(Content[] content) {
		this.content = content;
	}
	public Preroll[] getPreroll() {
		return preroll;
	}
	public void setPreroll(Preroll[] preroll) {
		this.preroll = preroll;
	}
}
