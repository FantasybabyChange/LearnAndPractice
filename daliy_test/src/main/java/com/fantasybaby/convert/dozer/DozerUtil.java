package com.fantasybaby.convert.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author liuxi
 * @date2017年12月13日 18:22
 */
public class DozerUtil {
    private final static Mapper mapper = new DozerBeanMapper();
    public static void convertObject(Object origin,Object destination){
        mapper.map(origin,destination);

    }

    public static Object convertObject(Object origin,Class classT){
        return mapper.map(origin, classT);

    }

}
