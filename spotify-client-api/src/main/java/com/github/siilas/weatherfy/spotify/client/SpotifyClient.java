package com.github.siilas.weatherfy.spotify.client;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.siilas.weatherfy.spotify.model.Genre;

@Component
public class SpotifyClient {

	@Value("${spotify.app.host}")
	private String host;

	@Value("${spotify.app.client.id}")
	private String clienteId;

	@Value("${spotify.app.client.secret}")
	private String clientSecret;

	@Autowired
	private RestTemplate restTemplate;

	public String getMusicByGenre(Genre genre) {
		String url = host.concat("/v1/recommendations?market=BR").concat("&seed_genres=").concat(genre.getValue())
				.concat("&limit=10");
		HttpHeaders headers = new HttpHeaders();
		String auth = clienteId + ":" + clientSecret;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);
		return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
	}

}
