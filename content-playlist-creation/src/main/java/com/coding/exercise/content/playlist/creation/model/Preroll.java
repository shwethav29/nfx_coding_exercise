package com.coding.exercise.content.playlist.creation.model;

import java.util.List;

public class Preroll {
	private String name;
	private List<Video> videos;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
}
