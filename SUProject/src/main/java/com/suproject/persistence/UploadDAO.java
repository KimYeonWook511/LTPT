package com.suproject.persistence;

import java.util.List;

import com.suproject.domain.UploadDTO;
import com.suproject.domain.UploadVO;

public interface UploadDAO {

	public void createUpload(UploadDTO dto) throws Exception;
	public List<UploadVO> listUpload(int bno) throws Exception;
	public List<UploadVO> listNotNullUpload(int bno) throws Exception;
	public void updateUpload(UploadDTO dto) throws Exception;
	public void deleteUpload(int bno) throws Exception;
	public UploadVO readUpload(UploadDTO dto) throws Exception;
}
