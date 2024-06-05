package com.suproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suproject.domain.EnrollVO;
import com.suproject.domain.TrainerVO;
import com.suproject.domain.UserVO;
import com.suproject.service.EnrollService;
import com.suproject.service.TrainerService;
import com.suproject.service.UserService;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPTController.class);
	
	@Inject
	private UserService userService;
	@Inject
	private EnrollService enrollService;
	@Inject
	private TrainerService trainerService;
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public void trainer_listGET(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("/trainer/member/list GET 실행");
		
		UserVO vo = (UserVO)request.getSession().getAttribute("loginVO");
		
		List<EnrollVO> enrollList = enrollService.memberList(vo.getUserid());
		Map<String, UserVO> userMap = new HashMap<String, UserVO>();
		
		for (int i = 0; i < enrollList.size(); i++) {
			vo = userService.readUser(enrollList.get(i).getMember());
			userMap.put(vo.getUserid(), vo);
		}
		
		model.addAttribute("enrollList", enrollList);
		model.addAttribute("userList", userMap);
	}
	
	@RequestMapping(value = "/trainer/view", method = RequestMethod.GET)
	public void trainer_viewGET(String userid, Model model) throws Exception {
		
		logger.info("/trainer/view GET 실행");
		
		TrainerVO vo = trainerService.readTrainer(userid);
		double ratingAvg = (double)vo.getRatingtotal() / vo.getRatingcnt();
		
		model.addAttribute("userVO", userService.readUser(userid));
		model.addAttribute("membership", enrollService.membership(userid));
		model.addAttribute("rating", String.format("%.1f", ratingAvg));
	}
}
