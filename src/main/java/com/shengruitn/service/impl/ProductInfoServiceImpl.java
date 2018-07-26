package com.shengruitn.service.impl;

import com.shengruitn.Enum.ProductStatusEnum;
import com.shengruitn.Enum.ResultEnum;
import com.shengruitn.dao.ProductInfoRepository;
import com.shengruitn.dataobject.ProductInfo;
import com.shengruitn.dto.CartDTO;
import com.shengruitn.exception.SellException;
import com.shengruitn.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOneByProId(String ProductId) {
        return repository.findOne(ProductId);
    }

    @Override
    public List<ProductInfo> findByCategoryType(Integer categoryType) {
        return repository.findByCategoryType(categoryType);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        return repository.findByProductStatus(productStatus);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    /* 加库存，不适用于并发处理 并发处理待 TODO */
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            productInfo.setProductStock( productInfo.getProductStock()+ cartDTO.getProductQuantity() );
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    /* 减库存，不适用于并发处理 并发处理待 TODO */
    public void dereaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());

        return repository.save(productInfo);
    }
}

