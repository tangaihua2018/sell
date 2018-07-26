package com.shengruitn.config;
/*
 *   项目中URL配置
 *   @author tangah
 *   @create 2018-07-01 23:47
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

    /* 微信公众平台授权URL */
    private String wechatMpAuthorize;
}
