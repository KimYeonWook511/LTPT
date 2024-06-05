package com.suproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.suproject.domain.EnrollDTO;
import com.suproject.domain.EnrollVO;

@Repository
public class EnrollDAOImpl implements EnrollDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.EnrollMapper";

	@Override
	public void createEnroll(EnrollDTO dto) throws Exception {
		sqlSession.insert(namespace + ".createEnroll", dto);
	}

	@Override
	public EnrollVO readEnroll(EnrollDTO dto) throws Exception {
		return (EnrollVO)sqlSession.selectOne(namespace + ".readEnroll", dto);
	}

	@Override
	public int membership(String trainer) throws Exception {
		return sqlSession.selectOne(namespace + ".membership", trainer);
	}
	
	@Override
	public void completecnt(EnrollDTO dto) throws Exception {
		sqlSession.update(namespace + ".completecnt", dto);
	}
	
	@Override
	public void totalcnt(EnrollDTO dto) throws Exception {
		sqlSession.update(namespace + ".totalcnt", dto);
	}
	
	@Override
	public List<EnrollVO> chkEnroll(String member) throws Exception {
		return sqlSession.selectList(namespace + ".chkEnroll", member);
	}
	
	@Override
	public List<EnrollVO> memberList(String trainer) throws Exception {
		return sqlSession.selectList(namespace + ".memberList", trainer);
	}
	
	@Override
	public List<EnrollVO> memberAllList() throws Exception {
		return sqlSession.selectList(namespace + ".memberAllList");
	}
}
