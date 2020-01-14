package com.tahoecn.xkc.schedule;

import com.tahoecn.xkc.XkcApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description TODO
 * @Author zefuw
 * @DATE 2020/1/9 11:15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XkcApplication.class)
public class CustomerTaskTest {
    @Autowired
    private CustomerTask customerTask;
    @Test
    public void pushCustomerToWxb() throws Exception {
        customerTask.pushCustomerToWxb();
    }

}