package com.fantasybaby.future;

/**
 * Data接口返回result
 */
public interface Data {
    default String getResult(){
        return "";
    }
}
