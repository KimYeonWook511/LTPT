package com.suproject.dao.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suproject.domain.EnrollDTO;
import com.suproject.persistence.EnrollDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class EnrollDAOReadEnroll {
	
	@Inject
	private EnrollDAO dao;
	
	@Test
	public void readEnrollTest() throws Exception {
		EnrollDTO dto = new EnrollDTO();
		dto.setMember(null);
		dto.setTrainer("tt");
		System.out.println("°á°ú : " + dao.readEnroll(dto));
	}
	
}
