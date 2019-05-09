package com.github.siilas.weatherfy.api.mapper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.openweathermap.response.City;
import com.github.siilas.weatherfy.openweathermap.response.Coordinates;
import com.github.siilas.weatherfy.openweathermap.response.Temperature;
import com.github.siilas.weatherfy.spotify.model.Genre;
import com.github.siilas.weatherfy.spotify.response.Album;
import com.github.siilas.weatherfy.spotify.response.Artist;
import com.github.siilas.weatherfy.spotify.response.ExternalUrls;
import com.github.siilas.weatherfy.spotify.response.Track;
import com.github.siilas.weatherfy.spotify.response.Tracks;

public class TrackMapperTest {

	@Test
	public void deveTestarTrackMapper() {
		TrackSuggestion.TracksWrapper tracks = new TrackSuggestion.TracksWrapper();
		tracks.setCity(getCity());
		tracks.setGenre(Genre.ROCK);
		tracks.setTracks(getTracks());

		TrackSuggestion converted = TrackMapper.INSTANCE.convert(tracks);
		
		assertEquals(tracks.getCity().getName(), converted.getCity());
		assertEquals(tracks.getCity().getCoordinates().getLatitude(), converted.getLatitude(), 0.0);
		assertEquals(tracks.getCity().getCoordinates().getLongitude(), converted.getLongitude(), 0.0);
		assertEquals(tracks.getCity().getTemperature().getValue(), converted.getTemperature(), 0.0);
		assertEquals(tracks.getCity().getDate(), converted.getRequestDate());
		assertEquals(tracks.getGenre(), converted.getGenre());
		assertEquals(tracks.getCity().getDate(), converted.getRequestDate());
	}		

	private City getCity() {
		return City.builder()
				.coordinates(getCoordinates())
				.date(LocalDateTime.of(2019, 5, 9, 14, 15))
				.temperature(getTemperature())
				.build();
	}
	
	private Coordinates getCoordinates() {
		return Coordinates.builder()
				.latitude(1.0)
				.longitude(2.0)
				.build();
	}
	
	private Temperature getTemperature() {
		return Temperature.builder()
				.value(15.0)
				.build();
	}
	
	private Tracks getTracks() {
		return Tracks.builder()
				.addTtrack(getTrack("Avantasia", "The Metal Opera Pt I", 2001, "Avantasia"))
				.addTtrack(getTrack("Save Me", "Rocket Ride", 2006, "Edguy"))
				.addTtrack(getTrack("Waiting Silence", "The Temple Of Shadows", 2004, "Angra"))
				.build();
	}
	
	private Track getTrack(String name, String album, Integer releaseDate, String artist) {
		return Track.builder()
				.name(name)
				.album(Album.builder().name(album).releaseDate(releaseDate).build())
				.addArtist(Artist.builder().name(artist).build())
				.externalUrls(ExternalUrls.builder().spotify("URL do Spotify").build())
				.build();
	}

}
