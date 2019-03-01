package com.github.siilas.weatherfy.spotify.client;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.siilas.weatherfy.core.http.HttpStatusUtils;
import com.github.siilas.weatherfy.spotify.config.SpotifyConfig;
import com.github.siilas.weatherfy.spotify.exception.AuthenticationException;
import com.github.siilas.weatherfy.spotify.exception.NoTracksFoundException;
import com.github.siilas.weatherfy.spotify.model.Genre;
import com.github.siilas.weatherfy.spotify.response.Authentication;
import com.github.siilas.weatherfy.spotify.response.Tracks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SpotifyClient {

	@Autowired
	private WebClient webClient;

    @Autowired
    private SpotifyConfig config;

    @Cacheable("tracks")
    public Mono<Tracks> getMusicByGenre(Genre genre) {
        return authorization()
        		.flatMap(auth -> findTracks(auth, genre));
    }
    
    private Mono<Authentication> authorization() {
    	return webClient.post()
    		.uri(uri -> uri.host(config.getAuth())
    				.scheme("https")
    				.path("/api/token")
    				.build())
    		.header("Authorization", "Basic " + Base64.encodeBase64String(config.getAuthentication()))
    		.body(fromFormData("grant_type", "client_credentials"))
    		.retrieve()
    		.onStatus(HttpStatusUtils::isNotSuccess, r -> {
    			throw new AuthenticationException();
    		})
            .bodyToMono(Authentication.class)
            .doOnEach(error -> log.error("Spotify authentication error", error));
    }
    
    private Mono<Tracks> findTracks(Authentication auth, Genre genre) {
    	return webClient.get()
	        	.uri(uri -> uri.host(config.getApi())
	    				.scheme("https")
	    				.path("/v1/recommendations")
	    				.queryParam("market", "BR")
	    				.queryParam("limit", "10")
	    				.queryParam("seed_genres", genre.getValue())
	    				.build())
	        	.header("Authorization", "Bearer " + auth.getToken())
	        	.retrieve()
	        	.onStatus(HttpStatusUtils::isNotSuccess, r -> {
	    			throw new NoTracksFoundException();
	    		})
	        	.bodyToMono(Tracks.class)
	        	.doOnEach(error -> log.error("Error getting tracks", error));
    }

}
