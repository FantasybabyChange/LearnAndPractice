package com.fantasybaby.convert.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        UserDO u = new UserDO();
        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        u.setRelations(map);
        UserDTO o = (UserDTO) DozerUtil.convertObject(u, UserDTO.class);
        Map<String, String> relations = o.getRelations();
        relations.forEach((key,value)->{
            System.out.println(key+"---"+value);
        });


    }

}
