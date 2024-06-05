package com.suproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.suproject.domain.UploadDTO;
import com.suproject.domain.UploadVO;

@Repository
public class UploadDAOImpl implements UploadDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.UploadMapper";

	@Override
	public void createUpload(UploadDTO dto) throws Exception {
		// 업로드 파일 레코드 생성
		sqlSession.insert(namespace + ".createUpload", dto);
	}

	@Override
	public List<UploadVO> listUpload(int bno) throws Exception {
		// 업로드 파일 이름 리스트로 불러오기
		return sqlSession.selectList(namespace + ".listUpload", bno);
	}
	
	@Override
	public List<UploadVO> listNotNullUpload(int bno) throws Exception {
		return sqlSession.selectList(namespace + ".listNotNullUpload", bno);
	}
	
	@Override
	public void updateUpload(UploadDTO dto) throws Exception {
		// 업로드 파일 레코드 수정
		sqlSession.update(namespace + ".updateUpload", dto);
	}

	@Override
	public void deleteUpload(int bno) throws Exception {
		// 업로드 파일 레코드 삭제
		sqlSession.delete(namespace + ".deleteUpload", bno);
	}
	
	@Override
	public UploadVO readUpload(UploadDTO dto) throws Exception {
		return (UploadVO)sqlSession.selectOne(namespace + ".readUpload", dto);
	}
}
