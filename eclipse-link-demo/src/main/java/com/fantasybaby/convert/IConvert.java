package com.fantasybaby.convert;

import java.io.File;



/**
 * @author reid.liu
 * @date 2018-09-19 19:13
 */
public interface IConvert<T> {
    void convert(File param);
    String deConvert(T t);
}
