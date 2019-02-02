package com.coding.exercise.content.playlist.creation.model;

public class Video {
	private String prerollName;
	private String name;
	private Attributes attributes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attribute) {
		this.attributes = attribute;
	}
	public String getPrerollName() {
		return prerollName;
	}
	public void setPrerollName(String prerollName) {
		this.prerollName = prerollName;
	}
	
}
