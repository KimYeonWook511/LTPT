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
import com.suproject.service.ApplyTrainerService;

public class ApplyTrainerGETInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyTrainerGETInterceptor.class);
	
	@Inject
	private ApplyTrainerService applyTrainerService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("ApplyWriteGET preHandle ����");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (applyTrainerService.readApplyTrainer(loginVO.getUserid()) != null) {
			
			logger.info("�̹� ��û �Ϸ�");
			request.setAttribute("msg", "�̹� ��û�ϼ̽��ϴ�.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/apply/viewApplyTrainer");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
	}
	
}
