package com.suproject.service;

import java.util.List;

import com.suproject.domain.ApplyTrainerDTO;
import com.suproject.domain.ApplyTrainerVO;

public interface ApplyTrainerService {
	
	public void registApplyTrainer(ApplyTrainerDTO dto) throws Exception;
	public ApplyTrainerVO readApplyTrainer(String userid) throws Exception;
	public void updateApplyTrainer(ApplyTrainerDTO dto) throws Exception;
	public void deleteApplyTrainer(String userid) throws Exception;
	public List<ApplyTrainerVO> listApplyTrainer() throws Exception;
}
