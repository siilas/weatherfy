package com.github.siilas.weatherfy.spotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.siilas.weatherfy.core.exception.WeatherfyException;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends WeatherfyException {

    private static final long serialVersionUID = 1072465693039017070L;

    public AuthenticationException() {
        super("Spotify authentication failed");
    }

}
