package com.suproject.persistence;

import java.util.List;

import com.suproject.domain.EnrollDTO;
import com.suproject.domain.EnrollVO;

public interface EnrollDAO {
	
	public void createEnroll(EnrollDTO dto) throws Exception; 
	public EnrollVO readEnroll(EnrollDTO dto) throws Exception;
	public int membership(String trainer) throws Exception;
	public void completecnt(EnrollDTO dto) throws Exception;
	public void totalcnt(EnrollDTO dto) throws Exception;
	public List<EnrollVO> chkEnroll(String member) throws Exception;
	public List<EnrollVO> memberList(String trainer) throws Exception;
	public List<EnrollVO> memberAllList() throws Exception;
}
