package com.suproject.service;

import java.util.List;

import com.suproject.domain.BoardVO;
import com.suproject.domain.BoardDTO;

public interface BoardService {
	
	public void registBoard(BoardDTO dto) throws Exception;
	public BoardVO readBoard(int bno) throws Exception;
	public void updateBoard(BoardDTO dto) throws Exception;
	public void deleteBoard(int bno) throws Exception;
	public List<BoardVO> listBoard(String writer) throws Exception; 
	public int lastBno() throws Exception;
	public List<BoardVO> listTrainerBoard(String trainer) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public void updateRating(BoardDTO dto) throws Exception;
}
