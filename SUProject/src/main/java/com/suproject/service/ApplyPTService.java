package com.suproject.service;

import java.util.List;

import com.suproject.domain.ApplyPTDTO;
import com.suproject.domain.ApplyPTVO;

public interface ApplyPTService {
	
	public void registApplyPT(ApplyPTDTO dto) throws Exception;
	public ApplyPTVO readApplyPT(String userid) throws Exception;
	public void updateApplyPT(ApplyPTDTO dto) throws Exception;
	public void deleteApplyPT(String userid) throws Exception;
	public List<ApplyPTVO> listApplyPT() throws Exception;
}
