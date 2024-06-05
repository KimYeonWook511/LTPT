package com.suproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginGETInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginGETInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("LoginGET preHandle ����");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginVO") != null) {
			logger.info("�̹� �α��� ���� (���� ����)");
			
		}
		
		return true;
	}
	
}
