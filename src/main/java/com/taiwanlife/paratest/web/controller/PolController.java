package com.taiwanlife.paratest.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pol")
public class PolController {

	@RequestMapping("/report1")
	public ModelAndView report1() {
		return new ModelAndView("pol/report1");
	}

}
