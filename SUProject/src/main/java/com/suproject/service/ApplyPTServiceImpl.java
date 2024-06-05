package com.suproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.ApplyPTDTO;
import com.suproject.domain.ApplyPTVO;
import com.suproject.persistence.ApplyPTDAO;

@Service
public class ApplyPTServiceImpl implements ApplyPTService {

	@Inject
	private ApplyPTDAO dao;

	@Override
	public void registApplyPT(ApplyPTDTO dto) throws Exception {
		dao.createApplyPT(dto);
	}

	@Override
	public ApplyPTVO readApplyPT(String userid) throws Exception {
		return dao.readApplyPT(userid);
	}

	@Override
	public void updateApplyPT(ApplyPTDTO dto) throws Exception {
		dao.updateApplyPT(dto);
	}

	@Override
	public void deleteApplyPT(String userid) throws Exception {
		dao.deleteApplyPT(userid);
	}

	@Override
	public List<ApplyPTVO> listApplyPT() throws Exception {
		return dao.listApplyPT();
	}
}
