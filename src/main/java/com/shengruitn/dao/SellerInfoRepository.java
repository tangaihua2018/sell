package com.shengruitn.dao;

import com.shengruitn.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
/**
* @Description:  卖家信息DAO
* @author       tangah
* @date         2018/7/11 23:35
*/
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
}
