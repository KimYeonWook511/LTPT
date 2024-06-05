package com.suproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.suproject.domain.UserDTO;
import com.suproject.domain.UserVO;
import com.suproject.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO userDAO;
	
	@Override
	public void joinUser(UserDTO dto) throws Exception {
		// 회원가입 서비스
		userDAO.joinUser(dto);
	}
	
	@Override
	public UserVO readUser(String userid) throws Exception {
		// 유저정보 불러오는 서비스
		return (UserVO)userDAO.readUser(userid);
	}
	
	@Override
	public List<UserVO> listTrainer() throws Exception {
		// 트레이너리스트 불러오는 서비스
		return userDAO.listTrainer();
	}
	
	@Override
	public void deleteUser(String userid, String userpw) throws Exception {
		// 회원탈퇴 서비스
		userDAO.deleteUser(userid, userpw);
	}
	
	@Override
	public UserVO loginUser(UserDTO dto) throws Exception {
		// 로그인 서비스
		return userDAO.readUser(dto.getUserid());
	}
	
	@Override
	public int logoutUser(String userid) throws Exception {
		// 로그아웃 서비스
		if (userDAO.readUser(userid) != null)	return 1;
		else return -1;
	}
	
	@Override
	public void updateAuth(UserDTO dto) throws Exception {
		userDAO.updateAuth(dto);
	}
}
