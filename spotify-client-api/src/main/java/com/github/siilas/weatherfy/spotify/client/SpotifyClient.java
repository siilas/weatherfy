package com.github.siilas.weatherfy.spotify.client;

import static com.github.siilas.weatherfy.core.http.HttpAuthUtils.getAuth;
import static com.github.siilas.weatherfy.core.http.HttpAuthUtils.getToken;
import static com.github.siilas.weatherfy.core.http.HttpStatusUtils.isNotSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.siilas.weatherfy.spotify.config.SpotifyConfig;
import com.github.siilas.weatherfy.spotify.exception.AuthenticationException;
import com.github.siilas.weatherfy.spotify.exception.NoTracksFoundException;
import com.github.siilas.weatherfy.spotify.model.Genre;
import com.github.siilas.weatherfy.spotify.response.Authentication;
import com.github.siilas.weatherfy.spotify.response.Tracks;

@Component
public class SpotifyClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpotifyConfig spotifyConfig;

    @Cacheable("tracks")
    public Tracks getMusicByGenre(Genre genre) {
        Authentication auth = authorization();
        String url = spotifyConfig.getApi().concat("/v1/recommendations?market=BR")
                .concat("&seed_genres=").concat(genre.getValue())
                .concat("&limit=10");
        ResponseEntity<Tracks> tracks = restTemplate.exchange(url, HttpMethod.GET, getAuth(auth.getToken()), Tracks.class);
        if (isNotSuccess(tracks)) {
            throw new NoTracksFoundException();
        }
        return tracks.getBody();
    }

    public Authentication authorization() {
        String url = spotifyConfig.getAuth().concat("/api/token");
        ResponseEntity<Authentication> auth = restTemplate.exchange(url, HttpMethod.POST,
                getToken(spotifyConfig.getAuthentication()), Authentication.class);
        if (isNotSuccess(auth)) {
            throw new AuthenticationException();
        }
        return auth.getBody();
    }

}
