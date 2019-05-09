package com.github.siilas.weatherfy.api.nonfuncionalrequirements;

import org.junit.Assert;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.api.response.TrackSuggestionFallback;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class NonFunctionalRequirementsSteps extends NonFunctionalRequirementsTools {

	@Before
    public void before() {
        spotifyApiServer.start();
        spotifyAuthServer.start();
        openWeatherMapsServer.start();
    }
    
    @After
    public void after() {
        spotifyApiServer.stop();
        spotifyAuthServer.stop();
        openWeatherMapsServer.stop();
    }
	
	@Quando("houver alguma falha na API do OpenWeatherMaps")
	public void quandoHouverAlgumaFalhaNaAPIDoOpenWeatherMaps() {
		mockErrorOpenWeatherMaps();
		mockSpotify();
	}

	@Quando("houver alguma falha na API do Spotify")
	public void quandoHouverAlgumaFalhaNaAPIDoSpotify() {
		mock("Lençóis Paulista", 25.0);
		mockErrorSpotify();
	}
	
	@Então("deve sugerir uma playlist genérica")
	public void entaoDeveSugerirUmaPlaylistGenerica() {
		TrackSuggestion response = getResponse("Lençóis Paulista");
        Assert.assertEquals(TrackSuggestionFallback.FALLBACK_MESSAGE, response.getMessage());
	}
	
}
