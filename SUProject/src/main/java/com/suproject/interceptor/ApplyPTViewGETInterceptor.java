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

public class ApplyPTViewGETInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyPTViewGETInterceptor.class);
	
	@Inject
	private ApplyPTService applyPTService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("ApplyPTView GET preHandle ����");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (applyPTService.readApplyPT(loginVO.getUserid()) == null) {
			
			logger.info("��û���� ����");
			request.setAttribute("msg", "PT ��û�� ���ּ���.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/apply/applyPT");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
	}
	
}
