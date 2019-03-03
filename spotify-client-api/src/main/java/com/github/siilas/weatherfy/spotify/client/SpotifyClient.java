package com.github.siilas.weatherfy.spotify.client;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.siilas.weatherfy.core.exception.GenericException;
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

    public static String getAuthPath() {
        return "/api/token";
    }
    
    public static String getRecommendationsPath() {
        return "/v1/recommendations";
    }
    
    @Cacheable("tracks")
    public Mono<Tracks> getMusicByGenre(Genre genre) {
        return authorization()
        		.flatMap(auth -> findTracks(auth, genre));
    }
    
    private Mono<Authentication> authorization() {
    	return webClient.post()
	    		.uri(uri -> uri.host(config.getAuthHost())
	    				.scheme(config.getScheme())
	    				.path(getAuthPath())
	    				.port(config.getAuthPort())
	    				.build())
	    		.header("Authorization", "Basic " + Base64.encodeBase64String(config.getAuthentication()))
	    		.body(fromFormData("grant_type", "client_credentials"))
	    		.retrieve()
	    		.onStatus(HttpStatusUtils::isNotAuthorized, r -> {
	    			throw new AuthenticationException();
	    		})
	    		.onStatus(HttpStatusUtils::isNotSuccess, r -> {
					throw new GenericException("Authentication error");
				})
	            .bodyToMono(Authentication.class)
	            .doOnError(error -> log.error("Spotify authentication error", error));
    }
    
    private Mono<Tracks> findTracks(Authentication auth, Genre genre) {
    	return webClient.get()
	        	.uri(uri -> uri.host(config.getApiHost())
	    				.scheme(config.getScheme())
	    				.path(getRecommendationsPath())
	    				.port(config.getApiPort())
	    				.queryParam("market", "BR")
	    				.queryParam("limit", "10")
	    				.queryParam("seed_genres", genre.getValue())
	    				.build())
	        	.header("Authorization", "Bearer " + auth.getToken())
	        	.retrieve()
	        	.onStatus(HttpStatusUtils::isNotFound, r -> {
	    			throw new NoTracksFoundException();
	    		})
	        	.onStatus(HttpStatusUtils::isNotSuccess, r -> {
					throw new GenericException("Error getting tracks");
				})
	        	.bodyToMono(Tracks.class)
	        	.doOnError(error -> log.error("Error getting tracks", error));
    }

}
