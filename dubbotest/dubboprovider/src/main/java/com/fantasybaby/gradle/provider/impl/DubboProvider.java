package com.fantasybaby.gradle.provider.impl;

import com.fatansybaby.gradle.service.IDubboService;
import org.apache.dubbo.config.annotation.Service;
@Service(version = "1.0.0")
public class DubboProvider implements IDubboService{
    public String getName() {
        return "=== DubboProvider Name ===";
    }
}
