package com.suproject.persistence;

import java.util.List;

import com.suproject.domain.BoardDTO;
import com.suproject.domain.BoardVO;

public interface BoardDAO {
	
	public void createBoard(BoardDTO dto) throws Exception;
	public BoardVO readBoard(int bno) throws Exception;
	public void updateBoard(BoardDTO dto) throws Exception;
	public void deleteBoard(int bno) throws Exception;
	public List<BoardVO> listBoard(String writer) throws Exception;
	public int lastBno() throws Exception;
	public List<BoardVO> listTrainerBoard(String trainer) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public void updateRating(BoardDTO dto) throws Exception;
}
