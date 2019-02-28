package com.github.siilas.weatherfy.spotify.exception;

public class NoTracksFoundException extends RuntimeException {

    private static final long serialVersionUID = 9029148526690448724L;

    public NoTracksFoundException() {
        super("No tracks found");
    }

}
