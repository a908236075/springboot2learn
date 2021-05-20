package com.xiaobo.springboot2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {
    private final static Logger LOGGER=LoggerFactory.getLogger(ESConfig.class);

    private String hostName;

    private String port;

    /*@Bean
    public RestHighLevelClient client(){
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost(host,port,"http")
        ));
    }*/






}
