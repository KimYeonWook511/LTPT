package com.suproject.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.suproject.domain.TrainerDTO;
import com.suproject.domain.TrainerVO;

@Repository
public class TrainerDAOImpl implements TrainerDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.TrainerMapper";

	@Override
	public void createTrainer(String trainer) throws Exception {
		sqlSession.insert(namespace + ".createTrainer", trainer);
	}

	@Override
	public TrainerVO readTrainer(String trainer) throws Exception {
		return (TrainerVO)sqlSession.selectOne(namespace + ".readTrainer", trainer);
	}
	
	@Override
	public void deleteTrainer(String trainer) throws Exception {
		sqlSession.delete(namespace + ".deleteTrainer", trainer);
	}
	
	@Override
	public void updateTrainer(TrainerDTO dto) throws Exception {
		sqlSession.update(namespace + ".updateTrainer", dto);
	}
}
