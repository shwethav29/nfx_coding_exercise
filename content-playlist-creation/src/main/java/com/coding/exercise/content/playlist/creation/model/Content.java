package com.coding.exercise.content.playlist.creation.model;

public class Content {

	private String name;
	
	private Preroll[] preroll;
	
	private Video[] videos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Preroll[] getPreroll() {
		return preroll;
	}

	public void setPreroll(Preroll[] preroll) {
		this.preroll = preroll;
	}

	public Video[] getVideos() {
		return videos;
	}

	public void setVideos(Video[] videos) {
		this.videos = videos;
	}

}
