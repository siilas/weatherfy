package com.github.siilas.weatherfy.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.siilas.weatherfy.core.exception.WeatherfyException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends WeatherfyException {

    private static final long serialVersionUID = 1990789719406465144L;
    
    public GenreNotFoundException() {
        super("Genre not found");
    }

}
