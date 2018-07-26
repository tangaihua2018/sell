package com.shengruitn.dao;
import com.shengruitn.dataobject.ProductInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void findOnetest() {
        ProductInfo productInfo = repository.findOne("1");
        if( productInfo != null )
            System.out.println(productInfo.toString());
        System.out.println("test ProductInfo...");
    }

    @Test
    public void findByProductIdInTest() {
        List<String> list = Arrays.asList("1","2");
        List<ProductInfo> productInfos = repository.findByProductIdIn(list);
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void findByProductTypeTest() {
        List<Integer> list = Arrays.asList(1,2);
        List<ProductInfo> productInfo = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, productInfo.size() );
        //System.out.println(productInfo.toString());
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = repository.findByProductStatus(1);
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    //@Transactional
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(1);
        productInfo.setProductId("21");
        productInfo.setProductIcon("http://test.com");
        productInfo.setProductName("蛙来了");
        productInfo.setProductDescription("四川的水煮青蛙，很正宗");
        productInfo.setProductPrice(new BigDecimal(56));
        productInfo.setProductStatus(1);
        productInfo.setProductStock(2);
        repository.save(productInfo);

    }
}