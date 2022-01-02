package com.taiwanlife.paratest.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taiwanlife.paratest.web.security.UserSession;

@Controller
public class FinErrorController implements ErrorController {

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("error");
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			mav.addObject("status", status);
			mav.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
		}
		UserSession.clearBreadcrumb();
		return mav;
	}

}
