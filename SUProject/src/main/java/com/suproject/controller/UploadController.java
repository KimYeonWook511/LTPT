package com.suproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.suproject.util.UploadFileUtils;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
//	@Resource(name = "uploadPath")
	private String uploadPath = "C:/Users/user/Documents/workspace-sts-3.9.18.RELEASE/SUProject/src/main/webapp/WEB-INF/views/video";
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() throws Exception {
		
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartHttpServletRequest multipartRequest, Model model) throws Exception {
				
		ArrayList<String> savedNames = UploadFileUtils.fileProcess(multipartRequest, uploadPath);
		
		for (int i = 0; i < savedNames.size(); i++) {
			logger.info("i(" + i + ") : " + savedNames.get(i));
		}
		
		return "main";
	}
	
}
