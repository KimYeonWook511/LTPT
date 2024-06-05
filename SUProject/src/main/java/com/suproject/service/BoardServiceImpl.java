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
		// 피드백 게시판 등록 서비스
		dao.createBoard(dto);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// 피드백 게시판 불러오는 서비스
		return dao.readBoard(bno);
	}

	@Override
	public void updateBoard(BoardDTO dto) throws Exception {
		// 피드백 게시판 수정 서비스
		dao.updateBoard(dto);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// 피드백 게시판 삭제 서비스
		dao.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> listBoard(String writer) throws Exception {
		// 피드백 게시판 리스트 불러오는 서비스
		return dao.listBoard(writer);
	}
	
	@Override
	public int lastBno() throws Exception {
		// 마지막으로 등록된 게시물 번호 가져오는 서비스
		return dao.lastBno();
	}
	
	@Override
	public List<BoardVO> listTrainerBoard(String trainer) throws Exception {
		// 트레이너의 피드백 게시판 리스트 불러오는 서비스
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
