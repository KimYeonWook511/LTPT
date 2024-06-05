package com.suproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.UploadDTO;
import com.suproject.domain.UploadVO;
import com.suproject.persistence.UploadDAO;

@Service
public class UploadServiceImpl implements UploadService {

	@Inject
	private UploadDAO dao;

	@Override
	public void registUpload(UploadDTO dto) throws Exception {
		// 업로드 파일 레코드 등록 서비스
		dao.createUpload(dto);
	}

	@Override
	public List<UploadVO> listUpload(int bno) throws Exception {
		// 업로드 파일 리스트 불러오는 서비스
		return dao.listUpload(bno);
	}
	
	@Override
	public List<UploadVO> listNotNullUpload(int bno) throws Exception {
		return dao.listNotNullUpload(bno);
	}
	
	@Override
	public void updateUpload(UploadDTO dto) throws Exception {
		// 업로드 파일 수정 서비스
		dao.updateUpload(dto);
	}

	@Override
	public void deleteUpload(int bno) throws Exception {
		// 업로드 파일 삭제 서비스
		dao.deleteUpload(bno);
	}
	
	@Override
	public UploadVO readUpload(UploadDTO dto) throws Exception {
		return dao.readUpload(dto);
	}
}
