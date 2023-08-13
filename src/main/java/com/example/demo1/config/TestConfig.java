package com.example.demo1.config;

import com.example.demo1.config.properties.MyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty( //必须满足才能注入  来控制Configuration是否生效
        prefix = "test.my", //属性key前缀
        name = {"name"}, //用来从application.properties中读取某个属性值。 该值为空，则返回false
        havingValue = "scc",// name不为空  进行比较 true 此config生效 false 此config不生效
        matchIfMissing = false // 缺少该配置属性时是否可以加载。如果为true，没有该配置属性时也会正常加载；反之则不会生效 默认false
)
@EnableConfigurationProperties({MyProperties.class})//指定属性类，使其生效
public class TestConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final MyProperties properties;

    public TestConfig(MyProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Object getTest() {
        Object o = new Object();
        logger.info("TestConfig  test init");
        logger.info("properties.name:{}", properties.getName());
        return o;
    }

}
