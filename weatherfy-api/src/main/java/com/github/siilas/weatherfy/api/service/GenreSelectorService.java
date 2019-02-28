package com.github.siilas.weatherfy.api.service;

import org.springframework.stereotype.Component;

import com.github.siilas.weatherfy.api.exception.GenreNotFound;
import com.github.siilas.weatherfy.openweathermap.response.Temperature;
import com.github.siilas.weatherfy.spotify.model.Genre;

@Component
public class GenreSelectorService {

    public Genre getGenre(Temperature temperature) {
        if (temperature.isAbove30Degrees()) {
            return Genre.PARTY;
        }
        if (temperature.isBetween15And30Degrees()) {
            return Genre.POP;
        }
        if (temperature.isBetween10And14Degrees()) {
            return Genre.ROCK;
        }
        if (temperature.isBelow10Degrees()) {
            return Genre.CLASSICAL;
        }
        throw new GenreNotFound();
    }

}