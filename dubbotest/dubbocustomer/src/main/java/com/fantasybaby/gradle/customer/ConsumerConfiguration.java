package com.fantasybaby.gradle.customer;/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究 --liuxi
 *****************************************************************************/

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: liuxi
 * @time: 2019/9/13 18:26
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.fantasybaby")
//@PropertySource("classpath:/customer.xml")
public class ConsumerConfiguration {

}
