package com.github.siilas.weatherfy.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weatherfy")
public class WeatherfyResource {

    @GetMapping
    public String teste() {
        return "ok";
    }

}
