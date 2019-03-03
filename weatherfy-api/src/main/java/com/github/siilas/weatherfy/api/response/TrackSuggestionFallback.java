package com.github.siilas.weatherfy.api.response;

import java.util.ArrayList;
import java.util.List;

import com.github.siilas.weatherfy.spotify.response.Album;
import com.github.siilas.weatherfy.spotify.response.Artist;
import com.github.siilas.weatherfy.spotify.response.ExternalUrls;
import com.github.siilas.weatherfy.spotify.response.Track;

public final class TrackSuggestionFallback {

	private TrackSuggestionFallback() {
		throw new IllegalStateException();
	}
	
	public static final String FALLBACK_MESSAGE = "Hey, aconteceu um erro :/ Mas não se preocupe, "
			+ " ainda separamos uma playlist pra você!";

	public static TrackSuggestion fallBack() {
		TrackSuggestion trackSuggetion = new TrackSuggestion();
		trackSuggetion.setMessage(FALLBACK_MESSAGE);
		List<Track> tracks = new ArrayList<>();
		tracks.add(track("Kiss And Make Up", "Dua Lipa", "Dua Lipa", "7jr3iPu4O4bTCVwLMbdU2i", 2015));
		tracks.add(track("XO", "Beyoncé", "Beyoncé", "04cxAqa9ZgLwvEskosNVsB", 2013));
		tracks.add(track("Whole Lotta Love", "Led Zeppelin", "Led Zeppelin II", "0hCB0YR03f6AmQaHbwWDe8", 1969));
		tracks.add(track("The Beatitudes", "Vladimir Martynov", "TheSacred Spirit Of Russia", "2L76dRJlXYkyakLS8fdUC2", 2014));
		trackSuggetion.setTracks(tracks);
		return trackSuggetion;
	}

	private static Track track(String name, String artist, String album, String url, Integer year) {
		Track track = new Track();
		track.setName(name);
		track.setArtists(List.of(new Artist(artist)));
		track.setExternalUrls(new ExternalUrls("https://open.spotify.com/track/" + url));
		track.setAlbum(new Album(album, year));
		return track;
	}

}
