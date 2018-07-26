package com.shengruitn.service;

import com.shengruitn.Enum.ProductStatusEnum;
import com.shengruitn.dataobject.ProductInfo;
import com.shengruitn.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOneByProId( String ProductId );

    List<ProductInfo> findByCategoryType(Integer categoryType);

    /* 新增或修改商品 */
    ProductInfo save(ProductInfo productInfo);

    /* 查询单个商品信息 */
    ProductInfo findOne(String productId);

    /*
    * 查询所以在架商品列表
    * */
    List<ProductInfo> findUpAll();

    /* 分页查询所有商品 */
    Page<ProductInfo> findAll(Pageable pageable);

    /* 根据商品状态查询 */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /* 加库存 */
    void increaseStock(List<CartDTO> cartDTOList);

    /* 减库存 */
    void dereaseStock(List<CartDTO> cartDTOList);

    /* 上架 */
    ProductInfo onSale(String productId);

    /* 下架 */
    ProductInfo offSale(String productId);

}
