package com.github.siilas.weatherfy.api.exception;

public class GenreNotFound extends RuntimeException {

    private static final long serialVersionUID = 1990789719406465144L;
    
    public GenreNotFound() {
        super("Genre not found");
    }

}
