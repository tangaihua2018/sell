package com.shengruitn.dao;

import com.shengruitn.Enum.ProductStatusEnum;
import com.shengruitn.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductIdIn(List<String> ProductIdList);
    List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypeList);
    List<ProductInfo> findByCategoryType(Integer categoryType);
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
