package com.suproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suproject.domain.BoardDTO;
import com.suproject.domain.BoardVO;
import com.suproject.domain.EnrollDTO;
import com.suproject.domain.EnrollVO;
import com.suproject.domain.FeedbackDTO;
import com.suproject.domain.FeedbackVO;
import com.suproject.domain.TrainerDTO;
import com.suproject.domain.UploadDTO;
import com.suproject.domain.UploadVO;
import com.suproject.domain.UserVO;
import com.suproject.service.BoardService;
import com.suproject.service.EnrollService;
import com.suproject.service.FeedbackService;
import com.suproject.service.TrainerService;
import com.suproject.service.UploadService;
import com.suproject.service.UserService;
import com.suproject.util.UploadFileUtils;

@Controller
@RequestMapping("/myPT")
public class MyPTController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPTController.class);
	
//	@Resource(name = "uploadPath")
	private String uploadPath = UploadFileUtils.UPLOADPATH;
	
	@Inject
	private BoardService boardService;
	@Inject
	private UploadService uploadService;
	@Inject
	private FeedbackService feedbackService;
	@Inject
	private EnrollService enrollService;
	@Inject
	private TrainerService trainerService;
	@Inject
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("listGET ����");
		UserVO loginVO = (UserVO)request.getSession().getAttribute("loginVO");
		List<BoardVO> boardList;		
		
		if (loginVO.getAuthority().equals("member")) {
			// ȸ�� �α��� ����
			boardList = boardService.listBoard(loginVO.getUserid());
			
		} else if (loginVO.getAuthority().equals("trainer")) {
			// Ʈ���̳� �α��� ����
			boardList = boardService.listTrainerBoard(loginVO.getUserid());
			
		} else {
			// ���� �α��� ����
			boardList = boardService.listAll();
			
		}
		
		Map<Integer, String> feedbackProgressMap = new HashMap<Integer, String>();
		Map<String, String> nameMap = new HashMap<String, String>();
		
		for (int i = 0; i < boardList.size(); i++) {
			
			int bno = boardList.get(i).getBno();
			EnrollVO enrollVO = enrollService.readEnroll(new EnrollDTO(boardList.get(i).getWriter(), boardList.get(i).getTrainer()));
			
			if (uploadService.listNotNullUpload(bno).size() == feedbackService.listFeedback(bno).size()) {
				// �ǵ�� �Ϸ�
				feedbackProgressMap.put(bno, "�ǵ�� �Ϸ�");
				
			} else {
				// �ǵ�� ���� ��
				feedbackProgressMap.put(bno, "�ǵ�� ���� ��");
				
			}
			nameMap.put(enrollVO.getMember(), enrollVO.getMembername());
			nameMap.put(enrollVO.getTrainer(), enrollVO.getTrainername());
		}
		
		model.addAttribute("list", boardList);
		model.addAttribute("feedbackProgressList" ,feedbackProgressMap);
		model.addAttribute("nameList", nameMap);
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGET(Model model, HttpServletRequest request) throws Exception {
		
		logger.info("writeGET ����");
		
		UserVO vo = (UserVO)request.getSession().getAttribute("loginVO");
		List<EnrollVO> trainerList = enrollService.chkEnroll(vo.getUserid());
		Map<String, String> nameMap = new HashMap<String, String>();
				
		for (int i = 0; i < trainerList.size(); i++) {
			String trainerid = trainerList.get(i).getTrainer();
			nameMap.put(trainerid, userService.readUser(trainerid).getUsername());
		}
		
		model.addAttribute("trainerList", trainerList);
		model.addAttribute("nameList", nameMap);
		
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePOST(@ModelAttribute BoardDTO dto, MultipartHttpServletRequest multipartRequest, RedirectAttributes rttr) throws Exception {
		
		logger.info("write POST ����");
		
		dto.setWriter(((UserVO)multipartRequest.getSession().getAttribute("loginVO")).getUserid());
		EnrollDTO enrollDTO = new EnrollDTO();
		enrollDTO.setMember(dto.getWriter());
		enrollDTO.setTrainer(dto.getTrainer());
		
		EnrollVO enrollVO = enrollService.readEnroll(enrollDTO); 
		if (!(enrollVO.getTotalcnt() - enrollVO.getCompletecnt() > 0)) {
			// ���� PT Ƚ���� ���� ��
			rttr.addFlashAttribute("msg", "��û ������ PTȽ���� �����ϴ�.");
			
			return "redirect:/myPT/list";
		}
		
		boardService.registBoard(dto);
		
		ArrayList<String> savedNames = UploadFileUtils.fileProcess(multipartRequest, uploadPath);
		
		for (int i = 0; i < savedNames.size(); i++) {
			UploadDTO uploadDTO = new UploadDTO();
			uploadDTO.setBno(boardService.lastBno());
			uploadDTO.setFilename(savedNames.get(i));
			uploadDTO.setVideono(i);
			uploadService.registUpload(uploadDTO);
			
		}
		
		rttr.addFlashAttribute("message", "�ǵ�� �Խñ��� ��ϵǾ����ϴ�.");
		return "redirect:/myPT/list";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void viewGET(int bno, Model model) throws Exception {
		
		logger.info("view GET ����");
		
		BoardVO boardVO = boardService.readBoard(bno);
		EnrollDTO enrollDTO = new EnrollDTO();
		enrollDTO.setMember(boardVO.getWriter());
		enrollDTO.setTrainer(boardVO.getTrainer());
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("videoList", uploadService.listNotNullUpload(bno));
		model.addAttribute("enrollVO", enrollService.readEnroll(enrollDTO));
		
		if (uploadService.listNotNullUpload(bno).size() == feedbackService.listFeedback(bno).size()) {
			model.addAttribute("feedbackProgress", "�ǵ�� �Ϸ�");
			
		} else {
			model.addAttribute("feedbackProgress", "�ǵ�� ���� ��");
			
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(int bno, RedirectAttributes rttr) throws Exception {
		
		logger.info("delete bno : " + bno);
		
		UploadFileUtils.fileDelete(uploadPath, uploadService.listUpload(bno));
		boardService.deleteBoard(bno);
		uploadService.deleteUpload(bno);
		feedbackService.deleteFeedback(bno);
		return "redirect:/myPT/list";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {
		
		logger.info("modify GET ����");
		
		model.addAttribute("boardVO", boardService.readBoard(bno));
		model.addAttribute("videoList", uploadService.listUpload(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardDTO dto, MultipartHttpServletRequest multipartRequest, RedirectAttributes rttr) throws Exception {
		
		logger.info("modify POST ����");
		
		List<UploadVO> deleteFileNames = uploadService.listUpload(dto.getBno());
		ArrayList<String> savedNames = UploadFileUtils.fileProcess(multipartRequest, uploadPath);
		
		for (int i = 0; i < savedNames.size(); i++) {
			
			if (savedNames.get(i) != null) {
				// db ����
				System.out.println("db����");
				UploadDTO uploadDTO = new UploadDTO();
				uploadDTO.setBno(dto.getBno());
				uploadDTO.setFilename(savedNames.get(i));
				uploadDTO.setVideono(i);
				uploadService.updateUpload(uploadDTO);
				
			} else {
				// ���� �������� �ʱ�
				deleteFileNames.get(i).setFilename(null);
				
			}
		}
		
		UploadFileUtils.fileDelete(uploadPath, deleteFileNames);
		boardService.updateBoard(dto);
		
		return "redirect:/myPT/list";
	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public void feedbackGET(FeedbackDTO dto, Model model) throws Exception {
		
		logger.info("feedback GET ����");
		BoardVO boardVO = boardService.readBoard(dto.getBno());
		FeedbackVO feedbackVO = feedbackService.readFeedback(dto);
		model.addAttribute("uploadVO", uploadService.listUpload(dto.getBno()).get(dto.getVideono()));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("feedbackVO", feedbackVO);
		model.addAttribute("trainerName", userService.readUser(feedbackVO.getWriter()).getUsername());
		model.addAttribute("membername", userService.readUser(boardVO.getWriter()).getUsername());
	}
	
	@RequestMapping(value = "/rating", method = RequestMethod.POST)
	public String ratingPOST(BoardDTO dto, RedirectAttributes rttr) throws Exception {
		
		logger.info("rating POST ����");
		
//		String trainer = feedbackService.listFeedback(dto.getBno()).get(0).getWriter();
		
		trainerService.updateTrainer(new TrainerDTO(feedbackService.listFeedback(dto.getBno()).get(0).getWriter(), dto.getRating()));
		boardService.updateRating(dto);
		
		rttr.addFlashAttribute("msg", "���� ����� �Ϸ�Ǿ����ϴ�.");
		return "redirect:/myPT/view?bno=" + dto.getBno();
	}
	
	
 /*
  * 
  * 
  * /myPT/trainer/** --------------------------------------------------------------------------------------------------------------------------------------------------------
  * 
  * 
  */ 
	
	
	@RequestMapping(value = "/trainer/write", method = RequestMethod.GET)
	public void t_writeGET(FeedbackDTO dto, Model model) throws Exception {
		
		logger.info("t_write GET ����");
		BoardVO boardVO = boardService.readBoard(dto.getBno());
		model.addAttribute("uploadVO", uploadService.readUpload(new UploadDTO(dto.getBno(), dto.getVideono())));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("feedbackVO", feedbackService.readFeedback(dto));
		model.addAttribute("membername", userService.readUser(boardVO.getWriter()).getUsername());
	}
	
	@RequestMapping(value = "/trainer/write", method = RequestMethod.POST)
	public String t_writePOST(FeedbackDTO dto, RedirectAttributes rttr) throws Exception {
		
		logger.info("t_write POST ����");
		
		if (feedbackService.readFeedback(dto) == null) { 
			// �ǵ���� �������� ���� ��� ���
			feedbackService.registFeedback(dto);
			
			if (uploadService.listNotNullUpload(dto.getBno()).size() == feedbackService.listFeedback(dto.getBno()).size()) {
				// �ǵ���� �� �ۼ����� ��� PT 1ȸ ����
				enrollService.completecnt(new EnrollDTO(boardService.readBoard(dto.getBno()).getWriter(), dto.getWriter()));
			}
			
		} else {
			// �ǵ���� ������ ��� ����
			feedbackService.updateFeedback(dto);
			
		}
		
		return "redirect:/myPT/view?bno=" + dto.getBno();
	}
}
