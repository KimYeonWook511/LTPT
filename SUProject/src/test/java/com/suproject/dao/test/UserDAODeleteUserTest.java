package com.suproject.dao.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suproject.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class UserDAODeleteUserTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void deleteUserTest() throws Exception {
		userDAO.deleteUser("ttt", "tt22");
	}
}
