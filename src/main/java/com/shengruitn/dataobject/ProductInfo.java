package com.shengruitn.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shengruitn.Enum.ProductStatusEnum;
import com.shengruitn.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    @Id
    // 商品代码
    private String productId;

    // 商品名称
    private String productName;

    // 单价
    private BigDecimal productPrice;

    //
    private Integer productStock;

    //
    private String productDescription;

    // 图标
    private String productIcon;

    // 状态 0:正常 1：下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    // 商品类型
    private Integer categoryType;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 状态
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

}
