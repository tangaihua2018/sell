package com.shengruitn.form;

import com.shengruitn.Enum.ProductStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductForm {
    // 商品代码
    @NotEmpty(message = "商品ID必填")
    private String productId;

    // 商品名称
    @NotEmpty(message = "名称必填")
    private String productName;

    // 单价
    @NotNull(message = "商品单价必填")
    private BigDecimal productPrice;

    //
    @NotNull(message = "库存必填")
    private Integer productStock;

    //
    @NotEmpty(message = "描述必填")
    private String productDescription;

    // 图标
    @NotEmpty(message ="图片必填")
    private String productIcon;

    // 状态 0:正常 1：下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    // 商品类型
    @NotNull(message ="商品类型必填")
    private Integer categoryType;
}
