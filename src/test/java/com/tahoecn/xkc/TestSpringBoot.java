package com.tahoecn.xkc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tahoecn.xkc.service.opportunity.IBOpportunityService;

/***
 * 代码生成器
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestSpringBoot {

	@Autowired
	private IBOpportunityService ibOpportunityService;

    @Test
    public void test(){
    	ibOpportunityService.mCustomerZYQDList_Select(null);
    }
}
