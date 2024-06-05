package com.suproject.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.suproject.domain.UserVO;
import com.suproject.service.ApplyPTService;

public class ApplyPTGETInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyPTGETInterceptor.class);
	
	@Inject
	private ApplyPTService applyPTService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("ApplyPT GET preHandle ����");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (applyPTService.readApplyPT(loginVO.getUserid()) != null) {
			
			logger.info("�̹� ��û �Ϸ�");
			request.setAttribute("msg", "�̹� ��û�ϼ̽��ϴ�.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/apply/viewApplyPT");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
	}
	
}
