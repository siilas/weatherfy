package com.github.siilas.weatherfy.spotify.exception;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1072465693039017070L;

    public AuthenticationException() {
        super("Authentication failed");
    }

}
