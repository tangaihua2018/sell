package com.shengruitn.service.impl;

import com.shengruitn.dataobject.ProductCategory;
import com.shengruitn.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, ((List) productCategoryList).size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(8));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void save() {
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setCategoryId(40);
        ProductCategory productCategory = categoryService.findOne(40);
//        productCategory.setCreateTime(null);
//        productCategory.setUpdateTime(null);
        productCategory.setCategoryName("苹果与");
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}