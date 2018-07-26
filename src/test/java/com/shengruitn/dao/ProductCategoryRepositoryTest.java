package com.shengruitn.dao;

import com.shengruitn.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
        log.info(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(6);
        productCategory.setCategoryName("业务");
        productCategory.setCategoryType(6);
        repository.save(productCategory);
//        ProductCategory productCategory = new ProductCategory( "zuixindongtai", 8 );
//        ProductCategory result = repository.save(productCategory);
//        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null,result );
    }

    @Test
    public void findByCataegoryTypeInTest() {
        List<Integer> list = Arrays.asList(5,6,8);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);

        System.out.println(result.size());
        //Assert.assertNotEquals(0, result.size() );
    }
}