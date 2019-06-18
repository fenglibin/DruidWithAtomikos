/** 
 * Project Name:atomikos  
 * Description: TODO ADD Description. 
 * Company: iFLYBANK FINANCIAL Technology Co., Ltd. 合肥科讯金服科技有限公司
 * date: 2019年1月27日 下午12:41:46
 * @author wayne
 */ 
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
