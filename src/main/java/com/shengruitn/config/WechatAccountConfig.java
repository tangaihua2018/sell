package com.shengruitn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 *   微信账号相关配置信息
 *   @author tangah
 *   @create 2018-06-30 0:30
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;

    private String mpReturnUrl;

    private String mchId;

    private String mchKey;

    private String keyPath;

    private String notifyUrl;

}
