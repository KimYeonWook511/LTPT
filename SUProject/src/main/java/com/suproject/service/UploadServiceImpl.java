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
		// ���ε� ���� ���ڵ� ��� ����
		dao.createUpload(dto);
	}

	@Override
	public List<UploadVO> listUpload(int bno) throws Exception {
		// ���ε� ���� ����Ʈ �ҷ����� ����
		return dao.listUpload(bno);
	}
	
	@Override
	public List<UploadVO> listNotNullUpload(int bno) throws Exception {
		return dao.listNotNullUpload(bno);
	}
	
	@Override
	public void updateUpload(UploadDTO dto) throws Exception {
		// ���ε� ���� ���� ����
		dao.updateUpload(dto);
	}

	@Override
	public void deleteUpload(int bno) throws Exception {
		// ���ε� ���� ���� ����
		dao.deleteUpload(bno);
	}
	
	@Override
	public UploadVO readUpload(UploadDTO dto) throws Exception {
		return dao.readUpload(dto);
	}
}
