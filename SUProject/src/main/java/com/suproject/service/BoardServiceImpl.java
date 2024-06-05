package com.suproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.BoardDTO;
import com.suproject.domain.BoardVO;
import com.suproject.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public void registBoard(BoardDTO dto) throws Exception {
		// �ǵ�� �Խ��� ��� ����
		dao.createBoard(dto);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// �ǵ�� �Խ��� �ҷ����� ����
		return dao.readBoard(bno);
	}

	@Override
	public void updateBoard(BoardDTO dto) throws Exception {
		// �ǵ�� �Խ��� ���� ����
		dao.updateBoard(dto);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// �ǵ�� �Խ��� ���� ����
		dao.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> listBoard(String writer) throws Exception {
		// �ǵ�� �Խ��� ����Ʈ �ҷ����� ����
		return dao.listBoard(writer);
	}
	
	@Override
	public int lastBno() throws Exception {
		// ���������� ��ϵ� �Խù� ��ȣ �������� ����
		return dao.lastBno();
	}
	
	@Override
	public List<BoardVO> listTrainerBoard(String trainer) throws Exception {
		// Ʈ���̳��� �ǵ�� �Խ��� ����Ʈ �ҷ����� ����
		return dao.listTrainerBoard(trainer);
	}
	
	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}
	
	@Override
	public void updateRating(BoardDTO dto) throws Exception {
		dao.updateRating(dto);
	}
}
