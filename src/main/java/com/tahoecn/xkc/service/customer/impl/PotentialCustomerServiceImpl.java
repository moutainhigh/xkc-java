package com.tahoecn.xkc.service.customer.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tahoecn.xkc.service.customer.IPotentialCustomerService;

@Service
@Transactional(readOnly=true)
public class PotentialCustomerServiceImpl implements IPotentialCustomerService {

}
