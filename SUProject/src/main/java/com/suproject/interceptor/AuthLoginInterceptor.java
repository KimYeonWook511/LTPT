package com.suproject.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthLoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginVO") == null) {
			logger.info("로그인 필요!!");
			request.setAttribute("loginResult", "로그인 필요");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login/");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
	}
}
