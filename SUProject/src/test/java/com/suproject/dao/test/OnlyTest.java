package com.suproject.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suproject.service.TrainerService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class OnlyTest {
	
	@Autowired
	private TrainerService trainerService;
	
	@Test
	public void testSession() throws Exception {

		trainerService.registTrainer("tt");
	}
}
