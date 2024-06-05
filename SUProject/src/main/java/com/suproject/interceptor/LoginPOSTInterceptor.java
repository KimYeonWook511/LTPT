package com.suproject.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginPOSTInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginPOSTInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		
		logger.info("loginPOST postHandle 실행");
		
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		Object vo = modelMap.get("loginVO");
		int loginResult = (int)modelMap.get("loginResult");
		
		if (loginResult != 1) {
			logger.info("로그인 실패");
			
		} else if (vo != null && loginResult == 1) {
			logger.info("로그인 세션 생성");
			session.setAttribute("loginVO", vo);
			request.setAttribute("loginResult", loginResult);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		}
	}
}
