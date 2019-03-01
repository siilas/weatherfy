package com.github.siilas.weatherfy.spotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoTracksFoundException extends RuntimeException {

    private static final long serialVersionUID = 9029148526690448724L;

    public NoTracksFoundException() {
        super("No tracks found");
    }

}
