package com.shengruitn.dataobject;

/**
 *  类目
 *  Create by tangah
 *  2018-6-22
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity //数据库映射成对象
@DynamicUpdate
@Data
public class ProductCategory {

    /* 类目 */
    @Id     //Id为主键
    @GeneratedValue     //自增类型
    private Integer categoryId;

    /* 类目名字 */
    private String categoryName;

    /* 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    /* Hibernate实体为什么要提供一个无参的构造函数 */
    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
