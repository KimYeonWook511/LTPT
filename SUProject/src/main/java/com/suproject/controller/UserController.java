package com.suproject.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suproject.domain.UserDTO;
import com.suproject.domain.UserVO;
import com.suproject.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinGET(@ModelAttribute UserVO userVO) throws Exception {
		logger.info("joinGET 실행");
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(@RequestParam String confirmuserpw, @ModelAttribute UserDTO dto, 
							RedirectAttributes rttr, HttpServletRequest request) throws Exception {			
		
		logger.info("joinPOST 실행");
		
		if (dto.getUserid().isEmpty()) {
			logger.info("아이디 빔");
			rttr.addFlashAttribute("joinResult", "empty_id");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		} else if (dto.getUserpw().isEmpty()) {
			logger.info("비밀번호 빔");
			rttr.addFlashAttribute("joinResult", "empty_pw");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		} else if (dto.getUsername().isEmpty()) {
			logger.info("이름 빔");
			rttr.addFlashAttribute("joinResult", "empty_name");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		} else if (dto.getCallnum().isEmpty()) {
			logger.info("전번 빔");
			rttr.addFlashAttribute("joinResult", "empty_callnum");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		}
		
		if (!confirmuserpw.equals(dto.getUserpw())) {
			rttr.addFlashAttribute("joinResult", "fail_pw");
			rttr.addFlashAttribute("dto", dto);			
			return "redirect:/user/join";
			
		}
		
		try {
			// 회원가입 성공
			userService.joinUser(dto);
			
		} catch (Exception e) {
			// 아이디 중복
			rttr.addFlashAttribute("joinResult", "fail_id");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		}
		
		// 인터셉터를 사용하지 않고 컨트롤러에서 세션 생성하기
		HttpSession session = request.getSession();
		session.setAttribute("loginVO", userService.readUser(dto.getUserid()));
		rttr.addFlashAttribute("joinResult", "success");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(HttpServletRequest request, Model model) throws Exception {
		logger.info("loginGET 실행");
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("loginVO");
		
		if (vo != null) {
			logger.info("tt");
			model.addAttribute("logoutResult", -1);
		}
	}
	
	@RequestMapping(value = "/loginPOST", method = RequestMethod.POST)
	public String loginPOST(@ModelAttribute UserDTO dto, HttpServletRequest request, Model model) throws Exception {
		logger.info("loginPOST 실행");
		
		UserVO vo = userService.loginUser(dto); 

		if (vo == null) {
			// 아이디가 존재하지 않을 때
			model.addAttribute("loginResult", -2);
			return "/user/login";
			
		} else if (!vo.getUserpw().equals(dto.getUserpw())) {
			// 비밀번호가 일치하지 않을 때
			model.addAttribute("loginResult", -1);
			model.addAttribute("dto", dto);
			return "/user/login";
			
		} else {
			// 성공적인 로그인
			model.addAttribute("loginResult", 1);
			model.addAttribute("loginVO", vo);
			return "/main";
		}	
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("logoutGET 실행");
		
		UserVO vo = (UserVO)request.getSession().getAttribute("loginVO");
		
		if (vo == null) {
			// 현재 로그인 상태가 아님
			logger.info("현재 로그인 상태 아님");
			rttr.addFlashAttribute("logoutResult", -1);
			return "redirect:/";
		}
		
		int result = userService.logoutUser(vo.getUserid());
		
		if (result == 1) {
			// 로그아웃 성공
			request.getSession().invalidate();
			rttr.addFlashAttribute("logoutResult", 1);
			return "redirect:/";
			
		}
		// 로그아웃 실패	
		logger.info(result + "");
		return "redirect:/error";
	}
}
