package com.github.siilas.weatherfy.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping("/docs")
	public ModelAndView docs() {
		return new ModelAndView("redirect:/swagger-ui.html");
	}

}
