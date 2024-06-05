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

import com.suproject.domain.ApplyTrainerDTO;
import com.suproject.domain.EnrollDTO;
import com.suproject.domain.EnrollVO;
import com.suproject.domain.TrainerVO;
import com.suproject.domain.UserDTO;
import com.suproject.domain.UserVO;
import com.suproject.service.ApplyPTService;
import com.suproject.service.ApplyTrainerService;
import com.suproject.service.EnrollService;
import com.suproject.service.TrainerService;
import com.suproject.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPTController.class);
	
	@Inject
	private UserService userService;
	@Inject
	private ApplyTrainerService applyTrainerService;
	@Inject
	private ApplyPTService applyPTService;
	@Inject
	private EnrollService enrollService;
	@Inject
	private TrainerService trainerService;
	
	@RequestMapping(value = "/trainer/list", method = RequestMethod.GET)
	public void trainer_listGET(Model model) throws Exception {
		
		logger.info("/admin/trainer/list GET 실행");
		
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
		
		model.addAttribute("list", trainerList);
		model.addAttribute("membershipList", membershipMap);
		model.addAttribute("ratingList", ratingMap);
	}
	
	@RequestMapping(value = "/trainer/view", method = RequestMethod.GET)
	public void trainer_viewGET(String userid, Model model) throws Exception {
		
		logger.info("/admin/trainer/view GET 실행");
		
		TrainerVO vo = trainerService.readTrainer(userid);
		double ratingAvg = (double)vo.getRatingtotal() / vo.getRatingcnt();
		
		model.addAttribute("userVO", userService.readUser(userid));
		model.addAttribute("membership", enrollService.membership(userid));
		model.addAttribute("rating", String.format("%.1f", ratingAvg));
	}
	
	@RequestMapping(value = "/trainer/memberAuth", method = RequestMethod.POST)
	public String trainer_memberAuthPOST(UserDTO dto) throws Exception {
		dto.setAuthority("member");
		userService.updateAuth(dto);
		trainerService.deleteTrainer(dto.getUserid());
		return "redirect:/admin/trainer/list";
	}
	
//	@RequestMapping(value = "/trainer/adminAuth", method = RequestMethod.POST)
//	public String trainer_adminAuthPOST(UserDTO dto) throws Exception {
//		// 어드민 권한 부여 (버튼 비활성화 해둠)
//		dto.setAuthority("admin");
//		userService.updateAuth(dto);
//		return "redirect:/admin/trainer/list";
//	}
	
	@RequestMapping(value = "/apply/applyPTList", method = RequestMethod.GET)
	public void apply_applyPTGET(Model model) throws Exception {
		
		logger.info("/admin/apply/applyPT GET 실행");
		model.addAttribute("list", applyPTService.listApplyPT());
	}
	
	@RequestMapping(value = "/apply/viewApplyPT", method = RequestMethod.GET)
	public void apply_viewApplyPTGET(String userid, Model model) throws Exception {
		
		logger.info("/admin/apply/viewApplyPT GET 실행");
		model.addAttribute("applyPTVO", applyPTService.readApplyPT(userid));
	}
	
	@RequestMapping(value = "/apply/accept", method = RequestMethod.POST)
	public String apply_acceptPOST(EnrollDTO dto) throws Exception {
		
		logger.info("/admin/apply/accept POST 실행");
		
		if (enrollService.readEnroll(dto) == null) {
			// 회원과 트레이너간의 PT 레코드가 존재하지 않을 경우 새롭게 등록
			enrollService.createEnroll(dto);
			
		} else {
			// 회원과 트레이너간의 PT 레코드가 존재할 경우 횟수만 추가
			enrollService.totalcnt(dto);
			
		}
		
		applyPTService.deleteApplyPT(dto.getMember());
		
		return "redirect:/admin/apply/applyPTList";
	}
	
	@RequestMapping(value = "/apply/refuse", method = RequestMethod.POST)
	public String apply_refusePOST(EnrollDTO dto) throws Exception {
		
		logger.info("/admin/apply/refuse POST 실행");
		
		applyPTService.deleteApplyPT(dto.getMember());
		
		return "redirect:/admin/apply/applyPTList";
	}
	
	@RequestMapping(value = "/apply/applyTrainerList", method = RequestMethod.GET)
	public void apply_applyTrainerGET(Model model) throws Exception {
		
		logger.info("/admin/apply/applyTrainer GET 실행");
		model.addAttribute("list", applyTrainerService.listApplyTrainer());
	}
	
	@RequestMapping(value = "/apply/viewApplyTrainer", method = RequestMethod.GET)
	public void apply_viewApplyTrainerGET(String userid, Model model) throws Exception {
		
		logger.info("/admin/apply/viewApplyTrainer GET 실행");
		model.addAttribute("applyTrainerVO", applyTrainerService.readApplyTrainer(userid));
	}
	
	@RequestMapping(value = "/apply/pass", method = RequestMethod.POST)
	public String apply_passPOST(ApplyTrainerDTO dto) throws Exception {
		
		logger.info("/admin/apply/pass POST 실행");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(dto.getUserid());
		userDTO.setAuthority("trainer");
		
		userService.updateAuth(userDTO);
		trainerService.registTrainer(dto.getUserid());
		applyTrainerService.deleteApplyTrainer(dto.getUserid());
		
		return "redirect:/admin/apply/applyTrainerList";
	}
	
	@RequestMapping(value = "/apply/fail", method = RequestMethod.POST)
	public String apply_failPOST(ApplyTrainerDTO dto) throws Exception {
		
		logger.info("/admin/apply/fail POST 실행");
		
		applyTrainerService.deleteApplyTrainer(dto.getUserid());
		
		return "redirect:/admin/apply/applyTrainerList";
	}
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public void trainer_listGET(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("/admin/member/list GET 실행");
		
		List<EnrollVO> enrollList = enrollService.memberAllList();
		Map<String, UserVO> userMap = new HashMap<String, UserVO>();
		
		for (int i = 0; i < enrollList.size(); i++) {
			UserVO vo = userService.readUser(enrollList.get(i).getMember());
			userMap.put(vo.getUserid(), vo);
		}
		
		model.addAttribute("enrollList", enrollList);
		model.addAttribute("userList", userMap);
	}
}
