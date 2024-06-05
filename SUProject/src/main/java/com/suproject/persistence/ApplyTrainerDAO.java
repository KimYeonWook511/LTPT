package com.suproject.persistence;

import java.util.List;

import com.suproject.domain.ApplyTrainerDTO;
import com.suproject.domain.ApplyTrainerVO;

public interface ApplyTrainerDAO {
	
	public void createApplyTrainer(ApplyTrainerDTO dto) throws Exception;
	public ApplyTrainerVO readApplyTrainer(String userid) throws Exception;
	public void updateApplyTrainer(ApplyTrainerDTO dto) throws Exception;
	public void deleteApplyTrainer(String userid) throws Exception;
	public List<ApplyTrainerVO> listApplyTrainer() throws Exception;
}
