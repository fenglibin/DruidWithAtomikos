package net.xiake6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xiake6.dao.ds1.UserMapper;
import net.xiake6.model.ds1.User;

/**
 * 
 * ClassName UserService.java
 * @author fenglibin
 * @Blog http://xiake6.net
 * @Date 2019年6月18日
 * 
 * Description
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public int insert(User record) {
		int result = userMapper.insert(record);
		return result;
	}
	
}
