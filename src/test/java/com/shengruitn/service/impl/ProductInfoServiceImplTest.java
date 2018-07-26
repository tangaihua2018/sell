package com.shengruitn.service.impl;

import com.shengruitn.Enum.ProductStatusEnum;
import com.shengruitn.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl proInfoService;

    @Test
    public void findOneByProId() {
        ProductInfo productInfo = proInfoService.findOneByProId("1");
        System.out.println(productInfo.toString());
    }

    @Test
    public void findByCategoryType() {
        List<ProductInfo> productInfos = proInfoService.findByCategoryType(ProductStatusEnum.DOWN.getCode());
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("512");
        productInfo.setProductName("干锅鱿鱼");
        productInfo.setProductDescription("正宗川菜，干锅经典！");
        productInfo.setProductPrice(new BigDecimal(48));
        productInfo.setProductStock(1);
        productInfo.setProductStatus(1);
        productInfo.setProductIcon("http://jpa.com");
        productInfo.setCategoryType(2);
        proInfoService.save(productInfo);

    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = proInfoService.findByProductStatus(1);
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findupAll() {
        List<ProductInfo> productInfos = proInfoService.findUpAll();
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfos = proInfoService.findAll(request);
        System.out.println(productInfos.getTotalElements());
    }

    @Test
    public void onSaleTest() {
        proInfoService.onSale("2");

    }

    @Test
    public void offSale() {
        proInfoService.offSale("1");
    }
}