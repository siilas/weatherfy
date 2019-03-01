package com.github.siilas.weatherfy.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GenreNotFound extends RuntimeException {

    private static final long serialVersionUID = 1990789719406465144L;
    
    public GenreNotFound() {
        super("Genre not found");
    }

}
