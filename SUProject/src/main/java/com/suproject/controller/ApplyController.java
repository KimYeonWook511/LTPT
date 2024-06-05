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

import com.suproject.domain.ApplyPTDTO;
import com.suproject.domain.ApplyTrainerDTO;
import com.suproject.domain.TrainerVO;
import com.suproject.domain.UserVO;
import com.suproject.service.ApplyPTService;
import com.suproject.service.ApplyTrainerService;
import com.suproject.service.EnrollService;
import com.suproject.service.TrainerService;
import com.suproject.service.UserService;

@Controller
@RequestMapping("/apply")
public class ApplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPTController.class);
	
	@Inject
	private UserService userService;
	@Inject
	private ApplyTrainerService applyTrainerService;
	@Inject
	private ApplyPTService applyPTService;
	@Inject
	private TrainerService trainerService;
	@Inject
	private EnrollService enrollService;
	
	@RequestMapping(value = "/applyTrainer", method = RequestMethod.GET)
	public void apply_applyTrainerGET() throws Exception {
		
		logger.info("/apply/applyTrainer GET 角青");	
	}
	
	@RequestMapping(value = "/applyTrainer", method = RequestMethod.POST)
	public String apply_applyTrainerPOST(ApplyTrainerDTO dto, HttpServletRequest request) throws Exception {
		
		logger.info("/apply/applyTrainer POST 角青");
		
		UserVO loginVO = (UserVO)request.getSession().getAttribute("loginVO");
		dto.setUserid(loginVO.getUserid());
		dto.setUsername(loginVO.getUsername());
		dto.setCallnum(loginVO.getCallnum());
		dto.setUsergender(loginVO.getUsergender());
		applyTrainerService.registApplyTrainer(dto);
		
		return "redirect:/apply/viewApplyTrainer";

	}
	
	@RequestMapping(value = "/viewApplyTrainer", method = RequestMethod.GET)
	public void apply_viewGET(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("/apply/viewApplyTrainer GET 角青");
		
		UserVO loginVO = (UserVO)request.getSession().getAttribute("loginVO");
		model.addAttribute("applyTrainerVO", applyTrainerService.readApplyTrainer(loginVO.getUserid()));
		
	}
	
	@RequestMapping(value = "/applyPT", method = RequestMethod.GET)
	public void apply_applyPTGET(Model model) throws Exception {
		
		logger.info("/apply/applyPT GET 角青");
		
		List<UserVO> trainerList = userService.listTrainer();
		Map<String, Integer> membershipMap = new HashMap<String, Integer>();
		Map<String, String> ratingMap = new HashMap<String, String>();
		
		for (int i = 0; i < trainerList.size(); i++) {
			String trainer = trainerList.get(i).getUserid();
			TrainerVO vo = trainerService.readTrainer(trainer);
			double ratingAvg = (double)vo.getRatingtotal() / vo.getRatingcnt();
			membershipMap.put(trainer, enrollService.membership(trainer));
			ratingMap.put(trainer, String.format("%.1f", ratingAvg));
		}
		
		model.addAttribute("trainerList", trainerList);
		model.addAttribute("membershipList", membershipMap);
		model.addAttribute("ratingList", ratingMap);
		
	}
	
	@RequestMapping(value = "/applyPT", method = RequestMethod.POST)
	public String apply_applyPTPOST(ApplyPTDTO dto, HttpServletRequest request) throws Exception {
		
		logger.info("/apply/applyPT POST 角青");
		UserVO loginVO = (UserVO)request.getSession().getAttribute("loginVO");
		dto.setUserid(loginVO.getUserid());
		dto.setUsername(loginVO.getUsername());
		dto.setUsergender(loginVO.getUsergender());
		dto.setCallnum(loginVO.getCallnum());
		dto.setTrainername(userService.readUser(dto.getTrainerid()).getUsername());
		applyPTService.registApplyPT(dto);
		
		return "redirect:/apply/viewApplyPT";
	}
	
	@RequestMapping(value = "/viewApplyPT", method = RequestMethod.GET)
	public void apply_viewApplyPTGET(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("/apply/viewApplyPT GET 角青");
		
		UserVO loginVO = (UserVO)request.getSession().getAttribute("loginVO");
		model.addAttribute("applyPTVO", applyPTService.readApplyPT(loginVO.getUserid()));
		
	}
}
