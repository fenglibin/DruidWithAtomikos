package net.xiake6.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.xiake6.model.ds1.User;
import net.xiake6.model.ds2.CustInfo;
import net.xiake6.service.UserService;
import net.xiake6.service.business.BatchInsertService;
import net.xiake6.service.business.BatchInsertServiceWrapper;

/**
 * 
 * ClassName TestInsert.java
 * 
 * @author fenglibin
 * @Blog http://xiake6.net
 * @Date 2019年6月18日
 * 
 *       Description
 */
@ContextConfiguration(value = { "classpath:spring-application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestInsert {
	private Logger logger = LoggerFactory.getLogger(TestInsert.class);
	@Autowired
	private BatchInsertService batchInsertService;
	@Autowired
	private UserService userService;
	@Autowired
	private BatchInsertServiceWrapper batchInsertServiceWrapper;

	@Test
	public void insert() {

		long startTime = System.currentTimeMillis();
		User user = new User();
		user.setName("User_" + (int) (Math.random() * 100));
		user.setAge((int) (Math.random() * 100));

		CustInfo info = new CustInfo();
		info.setPhone("123456789" + (int) (Math.random() * 100));
		batchInsertService.insert(user, info);

		long endTime = System.currentTimeMillis();
		logger.info("共耗时：{}毫秒", endTime - startTime);
	}

	@Test
	public void insertWithHystrix() {
		long startTime = System.currentTimeMillis();
		User user = new User();
		user.setName("User_" + (int) (Math.random() * 100));
		user.setAge((int) (Math.random() * 100));

		CustInfo info = new CustInfo();
		info.setPhone("123456789" + (int) (Math.random() * 100));
		logger.info("这是一个测试消息");
		try {
			int result = batchInsertService.insertWithHystrix(user, info);
		} catch (Exception e) {
			logger.error("插入的时候报异常:" + e.getMessage(), e);
		}
		long endTime = System.currentTimeMillis();
		logger.info("共耗时：{}毫秒", endTime - startTime);
	}
	
	@Test
	public void insertWithHystrixFromWrapper() {
		long startTime = System.currentTimeMillis();
		User user = new User();
		user.setName("User_" + (int) (Math.random() * 100));
		user.setAge((int) (Math.random() * 100));

		CustInfo info = new CustInfo();
		info.setPhone("123456789" + (int) (Math.random() * 100));
		logger.info("这是一个测试消息");
		int result = -1;
		try {
			result = batchInsertServiceWrapper.insertWithHystrix(user, info);
		} catch (Exception e) {
			logger.error("插入的时候报异常:" + e.getMessage(), e);
		}
		long endTime = System.currentTimeMillis();
		logger.info("共耗时：{}毫秒,结果是{}", endTime - startTime,result);
	}
}
