package com.fantasybaby.classloadertest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**将jarcopy到ext下面
 * 打印出sun.misc.Launcher$ExtClassLoader@511d50c0
 * 通过反射在app扫描阶段就将类加载进来 则不会从父加载器加载类
 * @author reid.liu
 * @date 2018-08-22 17:22
 */
public class ForceLoadInApp extends  ClassLoader{
    public static void loadBeforeBoot(){
        ClassLoader cl = ForceLoadInApp.class.getClassLoader();
        try {
            byte[] classData = getClassData("/home/fantasybaby/workspace/javaSeWorkspace/jvm-test/build/classes/java/main/com/fantasybaby/classloadertest/PrintClassPath.class");
            Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
            defineClass.setAccessible(true);
            defineClass.invoke(cl,classData,0,classData.length);
            defineClass.setAccessible(false);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public static byte[] getClassData(String path) {

        InputStream is = null;

        try {
//            URL url = new URL(path);

            byte[] buff = new byte[1024*4];

            int len = -1;

            is = new FileInputStream(new File(path));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while((len = is.read(buff)) != -1) {

                baos.write(buff,0,len);

            }

            return baos.toByteArray();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (is != null) {

                try {
                    is.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }
    public static void main(String[] args) {
//        ClassLoader cl = ForceLoadInApp.class.getClassLoader();
        loadBeforeBoot();
        new PrintClassPath().print();
    }
}
