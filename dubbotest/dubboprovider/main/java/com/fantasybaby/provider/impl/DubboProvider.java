package com.fantasybaby.provider.impl;

import com.fantasybaby.service.IDubboService;

public class DubboProvider implements IDubboService {
    public String getName() {
        return "=== DubboProvider Name ===";
    }
}
