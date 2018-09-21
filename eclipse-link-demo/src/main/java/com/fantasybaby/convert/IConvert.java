package com.fantasybaby.convert;

import java.io.File;
import java.util.List;


/**
 * @author reid.liu
 * @date 2018-09-19 19:13
 */
public interface IConvert<T> {
    List<T> convert(String param, Class<T> cls);
    String deConvert(T t);
}
