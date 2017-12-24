package com.fantasybaby.normaltest.autosetvalue;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantasybaby.normaltest.lombok.UserBean;

/**
 * @author liuxi
 * @date2017年12月23日 17:30
 */
public class AutoSetValueUtils {
    private static Map<String,Integer> eachCount = new HashMap<>();
    public static Object generateObjectWithValue(Class cls) throws IllegalAccessException, InstantiationException {
            Object o = cls.newInstance();
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                try {
                    Object o1 = generateValueByType(declaredField);
                    declaredField.setAccessible(true);
                    declaredField.set(o,o1);

                } catch (IllegalAccessException e) {
                }
            }
            return o;
    }

    public static List<Object> generateObjectWithValue(Class cls,int number) throws IllegalAccessException, InstantiationException {
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Object o = cls.newInstance();
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                try {
                    Object o1 = generateValueByType(declaredField);
                    declaredField.setAccessible(true);
                    declaredField.set(o,o1);

                } catch (IllegalAccessException e) {
                }
            }
            objects.add(o);
        }

        return objects;
    }
    private static  Object generateValueByType(Field declaredField){
        Type genericType = declaredField.getGenericType();
        Integer number = getNumber(declaredField.getName()+genericType.getTypeName());
        if(Integer.class.equals(genericType)){
            return number;
        }else if(String.class.equals(genericType)){
            return declaredField.getName()+"_"+number;
        }else if(Long.class.equals(genericType)){
            return number.longValue();
        }
        return null;
    }
    private static Integer  getNumber(String key){
        Integer integer = eachCount.get(key);
        if(integer == null){
            integer = 0;
        }else{
            integer++;
        }
        eachCount.put(key,integer);
        return integer;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
       /* UserBean userBean = (UserBean)AutoSetValueUtils.generateObjectWithValue(UserBean.class);
        System.out.println(userBean);
        UserBean userBean1 = (UserBean)AutoSetValueUtils.generateObjectWithValue(UserBean.class);
        System.out.println(userBean1);*/
        List<Object> objects = AutoSetValueUtils.generateObjectWithValue(UserBean.class, 4);
        for (Object object : objects) {
            UserBean userBean = (UserBean)object;
            System.out.println(userBean);
        }
    }


}
