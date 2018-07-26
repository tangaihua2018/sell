package com.shengruitn.service;

import com.shengruitn.dataobject.SellerInfo;

public interface SellerService {

    /* 通过openid查找用户 */
    SellerInfo findSellerInfoByOpenid(String openid);
}
