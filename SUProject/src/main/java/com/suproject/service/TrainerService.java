package com.suproject.service;

import com.suproject.domain.TrainerDTO;
import com.suproject.domain.TrainerVO;

public interface TrainerService {
	
	public void registTrainer(String trainer) throws Exception;
	public TrainerVO readTrainer(String trainer) throws Exception;
	public void deleteTrainer(String trainer) throws Exception;
	public void updateTrainer(TrainerDTO dto) throws Exception;
}
