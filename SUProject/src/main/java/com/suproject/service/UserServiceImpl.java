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
		// ȸ������ ����
		userDAO.joinUser(dto);
	}
	
	@Override
	public UserVO readUser(String userid) throws Exception {
		// �������� �ҷ����� ����
		return (UserVO)userDAO.readUser(userid);
	}
	
	@Override
	public List<UserVO> listTrainer() throws Exception {
		// Ʈ���̳ʸ���Ʈ �ҷ����� ����
		return userDAO.listTrainer();
	}
	
	@Override
	public void deleteUser(String userid, String userpw) throws Exception {
		// ȸ��Ż�� ����
		userDAO.deleteUser(userid, userpw);
	}
	
	@Override
	public UserVO loginUser(UserDTO dto) throws Exception {
		// �α��� ����
		return userDAO.readUser(dto.getUserid());
	}
	
	@Override
	public int logoutUser(String userid) throws Exception {
		// �α׾ƿ� ����
		if (userDAO.readUser(userid) != null)	return 1;
		else return -1;
	}
	
	@Override
	public void updateAuth(UserDTO dto) throws Exception {
		userDAO.updateAuth(dto);
	}
}
