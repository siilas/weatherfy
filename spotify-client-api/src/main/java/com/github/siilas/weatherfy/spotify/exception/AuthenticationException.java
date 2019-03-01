package com.github.siilas.weatherfy.spotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1072465693039017070L;

    public AuthenticationException() {
        super("Spotify authentication failed");
    }

}
