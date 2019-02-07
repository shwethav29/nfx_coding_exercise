package com.coding.exercise.content.playlist.creation.model;

import java.util.List;

public class Content {

	private String name;
	
	private List<Preroll> preroll;
	
	private List<Video> videos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Preroll> getPreroll() {
		return preroll;
	}

	public void setPreroll(List<Preroll> preroll) {
		this.preroll = preroll;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}


}
