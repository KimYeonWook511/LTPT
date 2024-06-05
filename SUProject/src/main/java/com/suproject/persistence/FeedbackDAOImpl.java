package com.suproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.suproject.domain.FeedbackDTO;
import com.suproject.domain.FeedbackVO;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.FeedbackMapper";

	@Override
	public void createFeedback(FeedbackDTO dto) throws Exception {
		sqlSession.insert(namespace + ".createFeedback", dto);
	}

	@Override
	public FeedbackVO readFeedback(FeedbackDTO dto) throws Exception {
		return (FeedbackVO)sqlSession.selectOne(namespace + ".readFeedback", dto);
	}

	@Override
	public void updateFeedback(FeedbackDTO dto) throws Exception {
		sqlSession.update(namespace + ".updateFeedback", dto);
	}

	@Override
	public void deleteFeedback(int bno) throws Exception {
		sqlSession.delete(namespace + ".deleteFeedback", bno);
	}
	
	@Override
	public List<FeedbackVO> listFeedback(int bno) throws Exception {
		return sqlSession.selectList(namespace + ".listFeedback", bno);
	}
}
