package com.shengruitn.service.impl;

import com.shengruitn.dao.SellerInfoRepository;
import com.shengruitn.dataobject.SellerInfo;
import com.shengruitn.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangah
 * @Title: SellerServiceImpl
 * @ProjectName Sell
 * @Description: 卖家信息
 * @date 2018/7/12     0:19
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
