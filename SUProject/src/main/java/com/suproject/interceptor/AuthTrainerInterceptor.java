package com.suproject.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.suproject.domain.UserVO;

public class AuthTrainerInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTrainerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("/myPT/trainer/** preHandle 실행");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (loginVO.getAuthority().equals("trainer") || loginVO.getAuthority().equals("admin")) {
			// 트레이너 및 관리자 로그인
			return true;
			
		}
		
		logger.info("올바르지 않은 사용자의 접근");
		request.setAttribute("msg", "허용되지 않은 접근입니다.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main");
		dispatcher.forward(request, response);
		return false;
	}
	
}
