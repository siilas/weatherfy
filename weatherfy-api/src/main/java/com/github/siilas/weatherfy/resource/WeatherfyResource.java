package com.github.siilas.weatherfy.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
public class WeatherfyResource {

    @GetMapping("/city/{city}")
    public String getByCity(@PathVariable String city) {
        return "ok";
    }

    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public String getByLatitudeAndLongitude(@PathVariable String latitude, @PathVariable String longitude) {
        return "ok";
    }

}
