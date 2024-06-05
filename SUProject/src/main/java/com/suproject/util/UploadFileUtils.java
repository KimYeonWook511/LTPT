package com.suproject.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.suproject.domain.UploadVO;

public class UploadFileUtils {
	
	public static final String UPLOADPATH = "C:/Users/user/Documents/workspace-sts-3.9.18.RELEASE/SUProject/src/main/webapp/resources/video";
	
	public static ArrayList<String> fileProcess(MultipartHttpServletRequest multipartRequest, String uploadPath) throws Exception {
		
		ArrayList<String> savedNames = new ArrayList<String>();
		Iterator<String> multi = multipartRequest.getFileNames();
		
		while (multi.hasNext()) {
			String fileName = multi.next();
			MultipartFile file = multipartRequest.getFile(fileName);
			
			if (file.getContentType() == null) {
				System.out.println("타입 null");
				savedNames.add(null);
			
			} else if (file.getOriginalFilename().equals("") || file.getContentType().equals("application/octet-stream")) {
				System.out.println("타입 빈칸 혹은 옥텟");
				savedNames.add(null);
				
			} else if (!file.getContentType().equals("video/mp4")) {
				System.out.println("비디오타입 아님");
				// 비디오 타입 아닌거 올렸을 때 업로드는 안되는데 알림창 띄우기 위해 코딩 필요
				savedNames.add(null);
				
			} else {
				savedNames.add(uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()));
				
			}			
		}
		
		return savedNames;
	}
	
	private static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		
		UUID uid = UUID.randomUUID(); // 랜덤한 키 값을 생성
		
		String savedName = uid.toString() + "_" + originalName;
		
		File target = new File(uploadPath, savedName);
		
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
	}
	
	public static void fileDelete(String uploadPath, List<UploadVO> deleteFileNames) throws Exception {
		
		for (int i = 0; i < deleteFileNames.size(); i++) {
			
			if (deleteFileNames.get(i).getFilename() != null)
			{
				// 등록되어 있는 파일 찾아서 삭제 처리
				File target = new File(uploadPath, deleteFileNames.get(i).getFilename());
				target.delete();
			}
		}
	}
}
