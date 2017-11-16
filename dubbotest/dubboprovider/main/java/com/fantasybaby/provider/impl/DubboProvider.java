package com.fantasybaby.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fantasybaby.service.IDubboService;
@Service(version = "1.0.0")
public class DubboProvider implements IDubboService {
    public String getName() {
        return "=== DubboProvider Name ===";
    }
}
