package com.suproject.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.TrainerDTO;
import com.suproject.domain.TrainerVO;
import com.suproject.persistence.TrainerDAO;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Inject
	private TrainerDAO dao;

	@Override
	public void registTrainer(String trainer) throws Exception {
		dao.createTrainer(trainer);
	}

	@Override
	public TrainerVO readTrainer(String trainer) throws Exception {
		return dao.readTrainer(trainer);
	}

	@Override
	public void deleteTrainer(String trainer) throws Exception {
		dao.deleteTrainer(trainer);
	}
	
	@Override
	public void updateTrainer(TrainerDTO dto) throws Exception {
		dao.updateTrainer(dto);
	}
}
