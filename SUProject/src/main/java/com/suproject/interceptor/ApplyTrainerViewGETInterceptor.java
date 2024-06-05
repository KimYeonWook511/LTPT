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

public class ApplyTrainerViewGETInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyTrainerViewGETInterceptor.class);
	
	@Inject
	private ApplyTrainerService applyTrainerService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("ApplyViewGET preHandle ����");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (applyTrainerService.readApplyTrainer(loginVO.getUserid()) == null) {
			
			logger.info("��û���� ����");
			request.setAttribute("msg", "Ʈ���̳� ��û�� ���ּ���.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/apply/applyTrainer");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
	}
	
}
