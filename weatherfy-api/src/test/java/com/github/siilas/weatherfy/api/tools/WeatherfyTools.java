package com.github.siilas.weatherfy.api.tools;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.github.siilas.weatherfy.api.mocks.OpenWeatherMapMocks;
import com.github.siilas.weatherfy.api.mocks.SpotifyApiMocks;
import com.github.siilas.weatherfy.api.mocks.SpotifyAuthMocks;
import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.openweathermap.client.OpenWeatherMapClient;
import com.github.siilas.weatherfy.spotify.client.SpotifyClient;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public abstract class WeatherfyTools {

    @Autowired
    private RestTemplate restTemplate;
    
    protected WireMockRule spotifyApiServer = new WireMockRule(options().port(8191));
    protected WireMockRule spotifyAuthServer = new WireMockRule(options().port(8192));
    protected WireMockRule openWeatherMapsServer = new WireMockRule(options().port(8193));

    protected void mock(String city, Double temperature) {
    	mockOpenWeatherMaps(city, temperature);
    	mockSpotify();
    }
    
    protected void mockOpenWeatherMaps(String city, Double temperature) {
    	openWeatherMapsServer.stubFor(get(urlMatching(OpenWeatherMapClient.getPath() + "(.*?)"))
                .withQueryParam("appid", matching("(.*?)"))
                .withQueryParam("q", equalTo(city))
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(HttpStatus.SC_OK)
                    .withBody(OpenWeatherMapMocks.SUCCESS_MOCK
                            .replace("CITY_NAME", city)
                            .replace("TEMPERATURE", String.valueOf(temperature)))));
    }
    
    protected void mockSpotify() {
    	spotifyAuthServer.stubFor(post(urlMatching(SpotifyClient.getAuthPath() + "(.*?)"))
                .withHeader("Authorization", matching("(.*?)"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(HttpStatus.SC_OK)
                    .withBody(SpotifyAuthMocks.SUCCESS_MOCK)));
        
        spotifyApiServer.stubFor(get(urlMatching(SpotifyClient.getRecommendationsPath() + "(.*?)"))
                .withHeader("Authorization", matching("(.*?)"))
                .withQueryParam("market", matching("(.*?)"))
                .withQueryParam("limit", matching("(.*?)"))
                .withQueryParam("seed_genres", matching("(.*?)"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(HttpStatus.SC_OK)
                    .withBody(SpotifyApiMocks.SUCCESS_MOCK)));
    }
    
    protected void mockErrorOpenWeatherMaps() {
    	openWeatherMapsServer.stubFor(get(urlMatching(OpenWeatherMapClient.getPath() + "(.*?)"))
                .withQueryParam("appid", matching("(.*?)"))
                .withQueryParam("q", matching("(.*?)"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR)));
    }
    
    protected void mockErrorSpotify() {
    	spotifyAuthServer.stubFor(post(urlMatching(SpotifyClient.getAuthPath() + "(.*?)"))
                .withHeader("Authorization", matching("(.*?)"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR)));
        
        spotifyApiServer.stubFor(get(urlMatching(SpotifyClient.getRecommendationsPath() + "(.*?)"))
                .withHeader("Authorization", matching("(.*?)"))
                .withQueryParam("market", matching("(.*?)"))
                .withQueryParam("limit", matching("(.*?)"))
                .withQueryParam("seed_genres", matching("(.*?)"))
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR)));
    }
    
    protected TrackSuggestion getResponse(String cidade) {
		String url = "http://localhost:8080/songs/city/" + cidade;
		return restTemplate.getForObject(url, TrackSuggestion.class);
	}

}
