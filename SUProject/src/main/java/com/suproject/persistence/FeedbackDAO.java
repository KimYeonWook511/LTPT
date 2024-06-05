package com.suproject.persistence;

import java.util.List;

import com.suproject.domain.FeedbackDTO;
import com.suproject.domain.FeedbackVO;

public interface FeedbackDAO {
	
	public void createFeedback(FeedbackDTO dto) throws Exception;
	public FeedbackVO readFeedback(FeedbackDTO dto) throws Exception;
	public void updateFeedback(FeedbackDTO dto) throws Exception;
	public void deleteFeedback(int bno) throws Exception;
	public List<FeedbackVO> listFeedback(int bno) throws Exception;
}
