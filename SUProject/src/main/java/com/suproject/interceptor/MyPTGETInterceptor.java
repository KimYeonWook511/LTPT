package com.suproject.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.suproject.domain.BoardVO;
import com.suproject.domain.EnrollDTO;
import com.suproject.domain.UserVO;
import com.suproject.service.BoardService;
import com.suproject.service.EnrollService;

public class MyPTGETInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPTGETInterceptor.class);
	
	@Inject
	private BoardService boardService;
	@Inject
	private EnrollService enrollService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("MyPT GET preHandle ����");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		if (loginVO.getAuthority().equals("member")) {
			// ȸ�� �α���
			if (loginVO.getUserid().equals(((BoardVO)boardService.readBoard(bno)).getWriter()))
				return true;
			
		} else if (loginVO.getAuthority().equals("trainer")) {
			// Ʈ���̳� �α���
			EnrollDTO dto = new EnrollDTO();
			dto.setMember(((BoardVO)boardService.readBoard(bno)).getWriter());
			dto.setTrainer(loginVO.getUserid());
			
			if (enrollService.readEnroll(dto) != null) 
				return true;
			
		} else if (loginVO.getAuthority().equals("admin")) {
			// ������ �α���
			return true;
			
		}
		
		logger.info("�ùٸ��� ���� ������� ����");
		request.setAttribute("msg", "������ ���� �����Դϴ�.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/myPT/list");
		dispatcher.forward(request, response);
		return false;
	}
	
}
