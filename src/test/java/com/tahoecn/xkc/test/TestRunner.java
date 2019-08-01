package com.tahoecn.xkc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.junit4.SpringRunner;

import com.tahoecn.xkc.XkcApplication;
import com.tahoecn.xkc.service.sys.ITaskService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = XkcApplication.class)
public class TestRunner {

	@Autowired
	private ITaskService saleService;

	@Test
	public void test() {
		try {
			saleService.taskRun("ServiceAutoCommission");
		} catch (TransientDataAccessResourceException e) {
			e.getCause().printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
