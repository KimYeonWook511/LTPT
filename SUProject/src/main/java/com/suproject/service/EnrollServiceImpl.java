package com.suproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.EnrollDTO;
import com.suproject.domain.EnrollVO;
import com.suproject.persistence.EnrollDAO;

@Service
public class EnrollServiceImpl implements EnrollService {

	@Inject
	private EnrollDAO dao;

	@Override
	public void createEnroll(EnrollDTO dto) throws Exception {
		dao.createEnroll(dto);
	}

	@Override
	public EnrollVO readEnroll(EnrollDTO dto) throws Exception {
		return dao.readEnroll(dto);
	}

	@Override
	public int membership(String trainer) throws Exception {
		return dao.membership(trainer);
	}

	@Override
	public void completecnt(EnrollDTO dto) throws Exception {
		dao.completecnt(dto);
	}

	@Override
	public void totalcnt(EnrollDTO dto) throws Exception {
		dao.totalcnt(dto);
	}
	
	@Override
	public List<EnrollVO> chkEnroll(String member) throws Exception {
		return dao.chkEnroll(member);
	}
	
	@Override
	public List<EnrollVO> memberList(String trainer) throws Exception {
		return dao.memberList(trainer);
	}
	
	@Override
	public List<EnrollVO> memberAllList() throws Exception {
		return dao.memberAllList();
	}
}
