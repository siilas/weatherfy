package com.github.siilas.weatherfy;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.apache.http.HttpStatus;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.siilas.weatherfy.mocks.OpenWeatherMapMocks;
import com.github.siilas.weatherfy.mocks.SpotifyApiMocks;
import com.github.siilas.weatherfy.mocks.SpotifyAuthMocks;
import com.github.siilas.weatherfy.openweathermap.client.OpenWeatherMapClient;
import com.github.siilas.weatherfy.spotify.client.SpotifyClient;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class CucumberStepTools {

    @Autowired
    protected RestTemplate restTemplate;
    
    protected WireMockRule spotifyApiServer = new WireMockRule(options().port(8191));
    protected WireMockRule spotifyAuthServer = new WireMockRule(options().port(8192));
    protected WireMockRule openWeatherMapsServer = new WireMockRule(options().port(8193));

    protected void mock(String city, Double temperature) {
        openWeatherMapsServer.stubFor(get(urlMatching(OpenWeatherMapClient.getPath() + "(.*?)"))
                .withQueryParam("appid", matching("(.*?)"))
                .withQueryParam("q", equalTo(city))
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(HttpStatus.SC_OK)
                    .withBody(OpenWeatherMapMocks.SUCCESS_MOCK
                            .replace("CITY_NAME", city)
                            .replace("TEMPERATURE", String.valueOf(temperature)))));
    
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

}
