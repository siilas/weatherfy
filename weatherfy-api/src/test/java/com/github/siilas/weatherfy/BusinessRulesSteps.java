package com.github.siilas.weatherfy;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.spotify.model.Genre;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;

public class BusinessRulesSteps extends CucumberStepTools {
    
    private String cidade;
    
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
    
    @Dado("que o usuário busque pela cidade {string}")
    public void dadoQueOUsuarioBusquePela(String cidade) {
        this.cidade = cidade;
    }

    @E("a temperatura esteja acima de {double} graus")
    public void eATemperaturaEstejaAcimaDeGraus(Double temperatura) {
    	mock(cidade, (temperatura + 1));
    }

    @Entao("deve sugerir músicas para festa")
    public void entaoDeveSugerirMusicasParaFesta() {
        TrackSuggestion response = getResponse();
        Assert.assertEquals(Genre.PARTY, response.getGenre());
    }

    @E("a temperatura esteja entre {double} e {double} graus")
    public void eATemperaturaEstejaEntreEGraus(Double temperaturaMinima, Double temperaturaMaxima) {
    	mock(cidade, ThreadLocalRandom.current().nextDouble(temperaturaMinima, temperaturaMaxima));
    }

    @Entao("deve sugerir músicas pop")
    public void entaoDeveSugerirMusicasPop() {
        TrackSuggestion response = getResponse();
        Assert.assertEquals(Genre.POP, response.getGenre());
    }

    @Entao("deve sugerir rock and roll")
    public void entaoDeveSugerirRockAndRoll() {
        TrackSuggestion response = getResponse();
        Assert.assertEquals(Genre.ROCK, response.getGenre());
    }

    @E("a temperatura esteja abaixo de {double} graus")
    public void eATemperaturaEstejaAbaixoDeGraus(Double temperatura) {
    	mock(cidade, (temperatura - 1));
    }

    @Entao("deve sugerir música clássica")
    public void entaoDeveSugerirMusicaClassica() {
        TrackSuggestion response = getResponse();
        Assert.assertEquals(Genre.CLASSICAL, response.getGenre());
    }
    
	private TrackSuggestion getResponse() {
		String url = "http://localhost:8080/songs/city/" + cidade;
		return restTemplate.getForEntity(url, TrackSuggestion.class).getBody();
	}

}
