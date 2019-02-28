package com.github.siilas.weatherfy.spotify.model;

import lombok.Getter;

@Getter
public enum Genre {

	PARTY("party"),
	POP("pop"),
	ROCK("rock"),
	CLASSICAL("classical");
	
	private final String value;
	
	Genre(String value) {
		this.value = value;
	}
	
}
