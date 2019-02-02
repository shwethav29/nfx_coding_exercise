package com.coding.exercise.content.playlist.creation.json.service;

import java.io.Reader;

import com.coding.exercise.content.playlist.creation.model.PlayListData;

public interface JsonParserService {

	public PlayListData parseJson(Reader reader);
}
