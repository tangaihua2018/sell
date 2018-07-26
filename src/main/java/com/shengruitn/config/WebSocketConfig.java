package com.shengruitn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author tangah
 * @Title: WebSocketConfig
 * @ProjectName Sell
 * @Description:
 * @date 2018/7/13     19:13
 */
@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
