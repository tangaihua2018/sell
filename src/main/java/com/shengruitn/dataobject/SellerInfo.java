package com.shengruitn.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;

/**
 * @author          tangah
 * @Title:          SellerInfo
 * @ProjectName     Sell
 * @Description:    卖家信息表
 * @date            2018/7/11     23:24
 */
@Entity
@Data
@DynamicUpdate
public class SellerInfo {

    /* 卖家编号 */
    @Id
    private String id;

    /* 卖家姓名 */
    private String username;

    /* 密码 */
    private String password;

    /* 微信openid */
    private String openid;

    private Date createTime;

    private Date updateTime;
}
