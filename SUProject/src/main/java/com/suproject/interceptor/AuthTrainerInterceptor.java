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
		
		logger.info("/myPT/trainer/** preHandle ����");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (loginVO.getAuthority().equals("trainer") || loginVO.getAuthority().equals("admin")) {
			// Ʈ���̳� �� ������ �α���
			return true;
			
		}
		
		logger.info("�ùٸ��� ���� ������� ����");
		request.setAttribute("msg", "������ ���� �����Դϴ�.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main");
		dispatcher.forward(request, response);
		return false;
	}
	
}
