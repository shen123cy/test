package com.example.demo1.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * application.properties 自定义属性
 */
@ConfigurationProperties(
        prefix = "test.my"
)
public class MyProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyProperties() {
    }

}
