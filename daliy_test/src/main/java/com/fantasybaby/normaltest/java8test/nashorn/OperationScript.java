/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.normaltest.java8test.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author reid.liu
 * @date 2019-03-03 20:57
 */
public class OperationScript {
    public static void main(String[] args) {
        String str = "3 < 4";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = null;
        try {
            result = engine.eval(str);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println("结果类型:" + result.getClass().getName() + ",计算结果:" + result);
    }
}
