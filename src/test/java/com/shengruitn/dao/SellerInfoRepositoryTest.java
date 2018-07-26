package com.shengruitn.dao;

import com.shengruitn.dataobject.SellerInfo;
import com.shengruitn.utils.KeyUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = repository.findByOpenid("345");
        Assert.assertEquals("345", sellerInfo.getOpenid());
    }

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("345");
        sellerInfo.setId("345");
        sellerInfo.setUsername("tangah");
        sellerInfo.setPassword("tangah");

        SellerInfo sellerInfo1 = repository.save(sellerInfo);
        Assert.assertNotNull( sellerInfo1);
    }
}