package com.github.siilas.weatherfy.spotify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.siilas.weatherfy.core.exception.WeatherfyException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoTracksFoundException extends WeatherfyException {

    private static final long serialVersionUID = 9029148526690448724L;

    public NoTracksFoundException() {
        super("No tracks found");
    }

}
