package com.shengruitn.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author          tangah
 * @Title:          CategoryForm
 * @ProjectName     Sell
 * @Description:    类目表单
 * @date            2018/7/11  16:22
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /* 类目名字 */
    @NotEmpty(message = "品类名称必填")
    private String categoryName;

    /* 类目编号 */
    @NotNull(message = "类型必填")
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}
