package net.xiake6.dao.ds2;

import net.xiake6.model.ds2.CustInfo;


public interface CustInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustInfo record);

    int insertSelective(CustInfo record);

    CustInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustInfo record);

    int updateByPrimaryKey(CustInfo record);
}