package net.xiake6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xiake6.dao.ds2.CustInfoMapper;
import net.xiake6.model.ds2.CustInfo;

/**
 * 
 * ClassName CustInfoService.java
 * @author fenglibin
 * @Blog http://xiake6.net
 * @Date 2019年6月18日
 * 
 * Description
 */
@Service
public class CustInfoService {
	@Autowired
	private CustInfoMapper custInfoMapper;
	
	public int insert(CustInfo record) {
		int result = custInfoMapper.insert(record);
		long now = System.currentTimeMillis();
		// 模拟一个异常
		if (now % 2 > -1) {
			throw new RuntimeException("CustInfoMapper throws test insert exception");
		}
		return result;
	}
	public int insertWithHystrix(CustInfo record) {
		int result = custInfoMapper.insert(record);
		long now = System.currentTimeMillis();
		// 模拟一个异常
		if (now % 2 > -1) {
			throw new RuntimeException("CustInfoMapper throws test insert exception");
		}
		return result;
	}
}
