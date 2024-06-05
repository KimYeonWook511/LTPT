package com.suproject.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public String mGET(Model model) {
		logger.info("server start");
			
		return "main";
	}
	
	@RequestMapping(value = "/main")
	public String mainGET(Model model) {
		logger.info("server start");
			
		return "main";
	}
	
}