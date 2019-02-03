package com.coding.exercise.content.playlist;

import java.util.ArrayList;
import java.util.List;

import com.coding.exercise.content.playlist.creation.model.Video;

/**
 * POJO class to define the legal playlists possible.
 * {@link LegalPlayListGeneratorImpl}
 * 
 * @author svasanth
 *
 */
public class LegalPlayListPlayer {
	private List<LegalPlayList> listOfPlayLists = new ArrayList<LegalPlayListPlayer.LegalPlayList>();

	public List<LegalPlayList> getListOfPlayLists() {
		return listOfPlayLists;
	}

	public void setListOfPlayLists(List<LegalPlayList> listOfPlayLists) {
		this.listOfPlayLists = listOfPlayLists;
	}

	public boolean addLegalPlayList(LegalPlayList legalPlayList) {
		return listOfPlayLists.add(legalPlayList);
	}

	public boolean isEmpty() {
		return listOfPlayLists.isEmpty();
	}

	public static class LegalPlayList {
		private List<Video> listOfVideos = new ArrayList<Video>();

		public List<Video> getListOfVideos() {
			return listOfVideos;
		}

		public void setListOfVideos(List<Video> listOfVideos) {
			this.listOfVideos = listOfVideos;
		}

		public boolean addToVideoList(Video video) {
			return listOfVideos.add(video);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder("{");
			for (Video video : listOfVideos) {
				builder.append(video.getName());
				builder.append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append("}");
			return builder.toString();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for (LegalPlayList legalPlayList : listOfPlayLists) {
			builder.append(listOfPlayLists.toString());
			builder.append(",");
		}
		if (listOfPlayLists.size() != 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("]");
		return builder.toString();
	}
}
