package com.suproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.ApplyTrainerDTO;
import com.suproject.domain.ApplyTrainerVO;
import com.suproject.persistence.ApplyTrainerDAO;

@Service
public class ApplyTrainerServiceImpl implements ApplyTrainerService {

	@Inject
	private ApplyTrainerDAO dao;

	@Override
	public void registApplyTrainer(ApplyTrainerDTO dto) throws Exception {
		dao.createApplyTrainer(dto);
	}

	@Override
	public ApplyTrainerVO readApplyTrainer(String userid) throws Exception {
		return dao.readApplyTrainer(userid);
	}

	@Override
	public void updateApplyTrainer(ApplyTrainerDTO dto) throws Exception {
		dao.updateApplyTrainer(dto);
	}

	@Override
	public void deleteApplyTrainer(String userid) throws Exception {
		dao.deleteApplyTrainer(userid);
	}

	@Override
	public List<ApplyTrainerVO> listApplyTrainer() throws Exception {
		return dao.listApplyTrainer();
	}
}
