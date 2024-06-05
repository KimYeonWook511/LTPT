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
		logger.info("joinGET ����");
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(@RequestParam String confirmuserpw, @ModelAttribute UserDTO dto, 
							RedirectAttributes rttr, HttpServletRequest request) throws Exception {			
		
		logger.info("joinPOST ����");
		
		if (dto.getUserid().isEmpty()) {
			logger.info("���̵� ��");
			rttr.addFlashAttribute("joinResult", "empty_id");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		} else if (dto.getUserpw().isEmpty()) {
			logger.info("��й�ȣ ��");
			rttr.addFlashAttribute("joinResult", "empty_pw");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		} else if (dto.getUsername().isEmpty()) {
			logger.info("�̸� ��");
			rttr.addFlashAttribute("joinResult", "empty_name");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		} else if (dto.getCallnum().isEmpty()) {
			logger.info("���� ��");
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
			// ȸ������ ����
			userService.joinUser(dto);
			
		} catch (Exception e) {
			// ���̵� �ߺ�
			rttr.addFlashAttribute("joinResult", "fail_id");
			rttr.addFlashAttribute("dto", dto);
			return "redirect:/user/join";
			
		}
		
		// ���ͼ��͸� ������� �ʰ� ��Ʈ�ѷ����� ���� �����ϱ�
		HttpSession session = request.getSession();
		session.setAttribute("loginVO", userService.readUser(dto.getUserid()));
		rttr.addFlashAttribute("joinResult", "success");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(HttpServletRequest request, Model model) throws Exception {
		logger.info("loginGET ����");
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("loginVO");
		
		if (vo != null) {
			logger.info("tt");
			model.addAttribute("logoutResult", -1);
		}
	}
	
	@RequestMapping(value = "/loginPOST", method = RequestMethod.POST)
	public String loginPOST(@ModelAttribute UserDTO dto, HttpServletRequest request, Model model) throws Exception {
		logger.info("loginPOST ����");
		
		UserVO vo = userService.loginUser(dto); 

		if (vo == null) {
			// ���̵� �������� ���� ��
			model.addAttribute("loginResult", -2);
			return "/user/login";
			
		} else if (!vo.getUserpw().equals(dto.getUserpw())) {
			// ��й�ȣ�� ��ġ���� ���� ��
			model.addAttribute("loginResult", -1);
			model.addAttribute("dto", dto);
			return "/user/login";
			
		} else {
			// �������� �α���
			model.addAttribute("loginResult", 1);
			model.addAttribute("loginVO", vo);
			return "/main";
		}	
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("logoutGET ����");
		
		UserVO vo = (UserVO)request.getSession().getAttribute("loginVO");
		
		if (vo == null) {
			// ���� �α��� ���°� �ƴ�
			logger.info("���� �α��� ���� �ƴ�");
			rttr.addFlashAttribute("logoutResult", -1);
			return "redirect:/";
		}
		
		int result = userService.logoutUser(vo.getUserid());
		
		if (result == 1) {
			// �α׾ƿ� ����
			request.getSession().invalidate();
			rttr.addFlashAttribute("logoutResult", 1);
			return "redirect:/";
			
		}
		// �α׾ƿ� ����	
		logger.info(result + "");
		return "redirect:/error";
	}
}
