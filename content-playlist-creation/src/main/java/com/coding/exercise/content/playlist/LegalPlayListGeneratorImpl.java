package com.coding.exercise.content.playlist;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.coding.exercise.content.playlist.LegalPlayListPlayer.LegalPlayList;
import com.coding.exercise.content.playlist.creation.model.PlayListData;
import com.coding.exercise.content.playlist.creation.model.Preroll;
import com.coding.exercise.content.playlist.creation.model.Video;
import com.coding.exercise.content.playlist.filter.PlayListFilterByContentIdCriteria;
import com.coding.exercise.content.playlist.filter.VideoFilterByCountryAndLanguage;
import com.coding.exercise.content.playlist.filter.VideoFilterByCountryCode;
/**
 * Generated the possible legal playlists for the given playlistData, matching the contentIdentifier and the country Code;
 * 
 * 
 * @author svasanth
 *
 */
public final class LegalPlayListGeneratorImpl implements LegalPlayListGenerator{

	
	/**
	 * Looks for the LegalPlayLists from the playlistData, matching content Identifier and the countryCode
	 * @param playListData 
	 * @param contentIdentifier
	 * @param countryCode
	 * @return
	 */
	public LegalPlayListPlayer getLegalPlaylists(PlayListData playListData,String contentIdentifier,String countryCode) {
		PlayListData filteredPlayList = new PlayListFilterByContentIdCriteria(contentIdentifier)
													.applyFilterCriteria(playListData);
		checkArgument(Optional.ofNullable(filteredPlayList.getContent()).isPresent(),"Zero or too many Contents for "+contentIdentifier);

		List<Video> contentVideosOfCountry = new VideoFilterByCountryCode(countryCode).filterVideos(Arrays.asList(filteredPlayList.getContent()[0].getVideos()));
		
		checkArgument(contentVideosOfCountry.size()>0,"No videos for the content "+contentIdentifier);
		
		LegalPlayListPlayer legalPlayListPlayer = new LegalPlayListPlayer();
		
		List<Video> prerollVideoForCountryAndLang = Stream.of(filteredPlayList.getPreroll()).
							flatMap(preroll -> (Stream.of(preroll.getVideos()).map(video -> {video.setPrerollName(preroll.getName()); return video;})))
							.filter(v -> Stream.of(v.getAttributes().getCountries()).collect(Collectors.toSet()).contains(countryCode))
							.filter(v -> (contentVideosOfCountry.stream().map(cv -> cv.getAttributes().getLanguage()).collect(Collectors.toSet())).contains(v.getAttributes().getLanguage()))
							.collect(Collectors.toList());
		List<String> listOfPrerollName = Stream.of(filteredPlayList.getContent()[0].getPreroll()).map(p -> p.getName()).collect(Collectors.toList());

		//for each content video check if legal playlist
		for (Video contentVideo:contentVideosOfCountry) {
			LegalPlayList legalPlayList = new LegalPlayList();
			for(String prerollName:listOfPrerollName) {
				List<Video> singlePrerollList = prerollVideoForCountryAndLang.stream().filter(v-> (v.getPrerollName().equals(prerollName)&&v.getAttributes().getLanguage().contentEquals(contentVideo.getAttributes().getLanguage()))).collect(Collectors.toList());
				checkArgument(singlePrerollList.size()< 2,"Incorrect preroll data");
				if(!singlePrerollList.isEmpty()) {
					legalPlayList.addToVideoList(singlePrerollList.get(0));
				}
			}
			if(listOfPrerollName.size()==legalPlayList.getListOfVideos().size()) {
				legalPlayList.addToVideoList(contentVideo);
				legalPlayListPlayer.addLegalPlayList(legalPlayList);
			}
		}
		
		if(legalPlayListPlayer.isEmpty()) {
			throw new NoLegalPlayListException("No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the "+countryCode);
		}
		return legalPlayListPlayer;
	}

}
