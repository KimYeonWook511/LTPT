package com.suproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.suproject.domain.BoardDTO;
import com.suproject.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.BoardMapper";

	@Override
	public void createBoard(BoardDTO dto) throws Exception {
		// 피드백 게시판 생성
		sqlSession.insert(namespace + ".createBoard", dto);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// 피드백 게시판 불러오기
		return (BoardVO)sqlSession.selectOne(namespace + ".readBoard", bno);
	}

	@Override
	public void updateBoard(BoardDTO dto) throws Exception {
		// 피드백 게시판 수정
		sqlSession.update(namespace + ".updateBoard", dto);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// 피드백 게시판 삭제
		sqlSession.delete(namespace + ".deleteBoard", bno);
	}

	@Override
	public List<BoardVO> listBoard(String writer) throws Exception {
		// 회원의 피드백 게시판 리스트 불러오기
		return sqlSession.selectList(namespace + ".listBoard", writer);
	}
	
	@Override
	public int lastBno() throws Exception {
		// 마지막으로 등록된 게시물 번호 불러오기
		return (int)sqlSession.selectOne(namespace + ".lastBno");
	}
	
	@Override
	public List<BoardVO> listTrainerBoard(String trainer) throws Exception {
		// 트레이너의 피드백 게시판 리스트 불러오기
		return sqlSession.selectList(namespace + ".listTrainerBoard", trainer);
	}
	
	@Override
	public List<BoardVO> listAll() throws Exception {
		// 피드백 게시판 리스트 전부 불러오기(어드민 권한 기능)
		return sqlSession.selectList(namespace + ".listAll");
	}
	
	@Override
	public void updateRating(BoardDTO dto) throws Exception {
		// 피드백 게시판 평점 등록하기
		sqlSession.update(namespace + ".updateRating", dto);
	}
	
}
