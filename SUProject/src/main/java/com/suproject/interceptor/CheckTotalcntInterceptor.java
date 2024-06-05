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
		
		logger.info("CheckTotalcnt preHandle 실행");
		
		HttpSession session = request.getSession();			
		UserVO loginVO = (UserVO)session.getAttribute("loginVO");
		
		if (userService.readUser(loginVO.getUserid()).getAuthority().equals("member")) {
			// 일반 회원일 경우에만 확인
			if (enrollService.chkEnroll(loginVO.getUserid()).size() == 0) {
				// PT 작성 불가능 (한번도 PT를 등록한 적이 없음)
				request.setAttribute("msg", "PT를 등록해주세요.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/apply/applyPT");
				dispatcher.forward(request, response);
				return false;
			}
		}
		// PT 작성 가능
		// 트레이너 및 관리자는 접근 가능
		return true;
	}
}
