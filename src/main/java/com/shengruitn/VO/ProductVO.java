package com.shengruitn.VO;

/*
 *   商品（包含类目）
 *   @author tangah
 *   @create 2018-06-26 16:31
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfo;
}
