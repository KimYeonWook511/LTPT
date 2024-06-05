package com.suproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.FeedbackDTO;
import com.suproject.domain.FeedbackVO;
import com.suproject.persistence.FeedbackDAO;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Inject
	private FeedbackDAO dao;

	@Override
	public void registFeedback(FeedbackDTO dto) throws Exception {
		dao.createFeedback(dto);
	}

	@Override
	public FeedbackVO readFeedback(FeedbackDTO dto) throws Exception {
		return dao.readFeedback(dto);
	}
	
	@Override
	public void updateFeedback(FeedbackDTO dto) throws Exception {
		dao.updateFeedback(dto);
	}

	@Override
	public void deleteFeedback(int bno) throws Exception {
		dao.deleteFeedback(bno);
	}
	
	@Override
	public List<FeedbackVO> listFeedback(int bno) throws Exception {
		return dao.listFeedback(bno);
	}
}
