package com.fantasybaby.gradle.provider;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: liuxi
 * @time: 2019/9/13 18:31
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.fantasybaby.gradle.provider")
@PropertySource("classpath:/provider.xml")
public class ProviderConfiguration {
}
