package com.suproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.suproject.domain.ApplyPTDTO;
import com.suproject.domain.ApplyPTVO;

@Repository
public class ApplyPTDAOImpl implements ApplyPTDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.ApplyPTMapper";

	@Override
	public void createApplyPT(ApplyPTDTO dto) throws Exception {
		sqlSession.insert(namespace + ".createApplyPT", dto);
	}

	@Override
	public ApplyPTVO readApplyPT(String userid) throws Exception {
		return (ApplyPTVO)sqlSession.selectOne(namespace + ".readApplyPT", userid);
	}

	@Override
	public void updateApplyPT(ApplyPTDTO dto) throws Exception {
		sqlSession.update(namespace + ".updateApplyPT", dto);
	}

	@Override
	public void deleteApplyPT(String userid) throws Exception {
		sqlSession.delete(namespace + ".deleteApplyPT", userid);
	}

	@Override
	public List<ApplyPTVO> listApplyPT() throws Exception {
		return sqlSession.selectList(namespace + ".listApplyPT");
	}
}
