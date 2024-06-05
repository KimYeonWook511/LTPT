package com.suproject.persistence;

import java.util.List;

import com.suproject.domain.UserDTO;
import com.suproject.domain.UserVO;

public interface UserDAO {
	
	public void joinUser(UserDTO dto) throws Exception;
	public UserVO readUser(String userid) throws Exception;
	public String deleteUser(String userid, String userpw) throws Exception;
	public List<UserVO> listTrainer() throws Exception;
	public void updateAuth(UserDTO dto) throws Exception;
}
