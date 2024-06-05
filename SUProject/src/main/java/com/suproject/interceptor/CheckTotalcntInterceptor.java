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
import com.suproject.service.EnrollService;
import com.suproject.service.UserService;

public class CheckTotalcntInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckTotalcntInterceptor.class);
	
	@Inject
	private EnrollService enrollService;
	@Inject
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		logger.info("CheckTotalcnt preHandle ����");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (userService.readUser(loginVO.getUserid()).getAuthority().equals("member")) {
			// �Ϲ� ȸ���� ��쿡�� Ȯ��
			if (enrollService.chkEnroll(loginVO.getUserid()).size() == 0) {
				// PT �ۼ� �Ұ��� (�ѹ��� PT�� ����� ���� ����)
				request.setAttribute("msg", "PT�� ������ּ���.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/apply/applyPT");
				dispatcher.forward(request, response);
				return false;
			}
		}
		// PT �ۼ� ����
		// Ʈ���̳� �� �����ڴ� ���� ����
		return true;
	}
}
