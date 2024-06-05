package com.suproject.dao.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suproject.domain.UserDTO;
import com.suproject.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class UserDAOJoinUserTest {
	
	@Inject
	private UserDAO userDAO;
	
	@Test
	public void insertUserTest() {
		try {
			UserDTO dto = new UserDTO();
			dto.setUserid("test");
			dto.setUserpw("tt");
			dto.setUsername("dd");
			dto.setUsergender("남자");
			userDAO.joinUser(dto);			
		} catch (Exception e) {
			System.out.println("실패");
		}
	}
}
