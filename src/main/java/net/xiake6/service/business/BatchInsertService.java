package net.xiake6.service.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.xiake6.model.ds1.User;
import net.xiake6.model.ds2.CustInfo;
import net.xiake6.service.CustInfoService;
import net.xiake6.service.UserService;

/**
 * 
 * ClassName BatchInsertService.java
 * @author fenglibin
 * @Blog http://xiake6.net
 * @Date 2019年6月18日
 * 
 * Description
 */
@Service
public class BatchInsertService {
	private Logger logger = LoggerFactory.getLogger(BatchInsertService.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CustInfoService custInfoService;
	@Transactional(rollbackFor= {Exception.class,RuntimeException.class})
	public void insert(User user,CustInfo custInfo) {
		int insertUser =  userService.insert(user);
		logger.info("insertUser={}",insertUser);
		int insertCustInfo = custInfoService.insert(custInfo);
		logger.info("insertCustInfo={}",insertCustInfo);
	}
}
