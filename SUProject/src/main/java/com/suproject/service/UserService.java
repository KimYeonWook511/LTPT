package com.suproject.service;

import java.util.List;

import com.suproject.domain.UserDTO;
import com.suproject.domain.UserVO;

public interface UserService {
	
	public void joinUser(UserDTO dto) throws Exception;
	public UserVO readUser(String userid) throws Exception;
	public List<UserVO> listTrainer() throws Exception;
	public void deleteUser(String userid, String userpw) throws Exception;
	public UserVO loginUser(UserDTO dto) throws Exception;
	public int logoutUser(String userid) throws Exception;
	public void updateAuth(UserDTO dto) throws Exception;
}
