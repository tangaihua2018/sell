package com.shengruitn.controller;/*
 *
 *   @author tangah
 *   @create 2018-06-26 15:51
 */

import com.shengruitn.VO.ProductInfoVO;
import com.shengruitn.VO.ProductVO;
import com.shengruitn.VO.ResultVO;
import com.shengruitn.dataobject.ProductCategory;
import com.shengruitn.dataobject.ProductInfo;
import com.shengruitn.service.CategoryService;
import com.shengruitn.service.ProductInfoService;
import com.shengruitn.utils.ResultVOUtil;
import org.apache.log4j.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductControllor {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResultVO list()
    {
        /* 1、查询所有的上架商品 */
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        /* 2、查询类目 */
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for ( ProductInfo productInfo : productInfoList )
//        {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        /* 精简方法：java8, lambda */
        List<Integer> categoryTypeList = productInfoList.stream()
                .map( e ->e.getCategoryType() )
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService
                .findByCategoryTypeIn(categoryTypeList);

        /* 3、数据拼装 */

        List<ProductVO> productVOList = new ArrayList<>();

        for ( ProductCategory productCategory : productCategoryList ) {
            ProductVO productVO = new ProductVO();
            productVOList.add(productVO);

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setProductInfo(productInfoVOList);

            for ( ProductInfo productInfo : productInfoList )
            {
                //if ( productInfo.getCategoryType() == productCategory.getCategoryId() ) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryId())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVOList.add(productInfoVO);

                    /* 有了它就不用一个个来拷贝了 */
                    BeanUtils.copyProperties(productInfo, productInfoVO);
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setProductPrice(productInfo.getProductPrice());
//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductDescription(productInfo.getProductDescription());
//                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                }
            }
        }

        return ResultVOUtil.success(productVOList);
    }

}
