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
		// �ǵ�� �Խ��� ����
		sqlSession.insert(namespace + ".createBoard", dto);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// �ǵ�� �Խ��� �ҷ�����
		return (BoardVO)sqlSession.selectOne(namespace + ".readBoard", bno);
	}

	@Override
	public void updateBoard(BoardDTO dto) throws Exception {
		// �ǵ�� �Խ��� ����
		sqlSession.update(namespace + ".updateBoard", dto);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// �ǵ�� �Խ��� ����
		sqlSession.delete(namespace + ".deleteBoard", bno);
	}

	@Override
	public List<BoardVO> listBoard(String writer) throws Exception {
		// ȸ���� �ǵ�� �Խ��� ����Ʈ �ҷ�����
		return sqlSession.selectList(namespace + ".listBoard", writer);
	}
	
	@Override
	public int lastBno() throws Exception {
		// ���������� ��ϵ� �Խù� ��ȣ �ҷ�����
		return (int)sqlSession.selectOne(namespace + ".lastBno");
	}
	
	@Override
	public List<BoardVO> listTrainerBoard(String trainer) throws Exception {
		// Ʈ���̳��� �ǵ�� �Խ��� ����Ʈ �ҷ�����
		return sqlSession.selectList(namespace + ".listTrainerBoard", trainer);
	}
	
	@Override
	public List<BoardVO> listAll() throws Exception {
		// �ǵ�� �Խ��� ����Ʈ ���� �ҷ�����(���� ���� ���)
		return sqlSession.selectList(namespace + ".listAll");
	}
	
	@Override
	public void updateRating(BoardDTO dto) throws Exception {
		// �ǵ�� �Խ��� ���� ����ϱ�
		sqlSession.update(namespace + ".updateRating", dto);
	}
	
}
