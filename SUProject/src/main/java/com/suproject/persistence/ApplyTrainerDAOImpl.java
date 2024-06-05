package com.suproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.suproject.domain.ApplyTrainerDTO;
import com.suproject.domain.ApplyTrainerVO;

@Repository
public class ApplyTrainerDAOImpl implements ApplyTrainerDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.ApplyTrainerMapper";

	@Override
	public void createApplyTrainer(ApplyTrainerDTO dto) throws Exception {
		sqlSession.insert(namespace + ".createApplyTrainer", dto);
	}

	@Override
	public ApplyTrainerVO readApplyTrainer(String userid) throws Exception {
		return (ApplyTrainerVO)sqlSession.selectOne(namespace + ".readApplyTrainer", userid); 
	}

	@Override
	public void updateApplyTrainer(ApplyTrainerDTO dto) throws Exception {
		sqlSession.update(namespace + ".updateApplyTrainer", dto);
	}

	@Override
	public void deleteApplyTrainer(String userid) throws Exception {
		sqlSession.delete(namespace + ".deleteApplyTrainer", userid);
	}

	@Override
	public List<ApplyTrainerVO> listApplyTrainer() throws Exception {
		return sqlSession.selectList(namespace + ".listApplyTrainer");
	}
}
