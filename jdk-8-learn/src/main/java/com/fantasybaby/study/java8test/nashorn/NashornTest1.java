package com.fantasybaby.study.java8test.nashorn;

import com.fantasybaby.study.java8test.change.ObjectFactory;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 * @author fantasybaby
 * @date 2018/2/16
 */
public class NashornTest1 {
    private static final String NASHORN_DEFAULT_NAME = "nashorn";
    private ScriptEngine se;
    public NashornTest1(){
         se = new ScriptEngineManager().getEngineByName(NASHORN_DEFAULT_NAME);
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource("nashorntest/test1.js").getFile();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            se.eval(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }
    /**
     * 通过nashorn引擎写javaScript
     */
    public void sayHelloByNashorn(){

        try {
            //可以使用eval方法来解析js语法
            se.eval("print('hello world!')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过javaScript文件来调用
     * 转换成Invocable接口
     * 调用js的相关函数
     */
    public void testNashornFromJsFile(){
            Invocable invocable = (Invocable) se;
        Object o = null;
        try {
            o = invocable.invokeFunction("fun1", "fantasybaby");
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(o);
    }

    /**
     * 通过javaScript文件来调用
     * 转换成Invocable接口
     * 调用js的相关函数
     */
    public void testNashornFromJsFileExtensions(){
        try {
            Invocable invocable = (Invocable) se;
            Object o = invocable.invokeFunction("fun3");
            System.out.println(o);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    /**
     * 在js调用java方法 查看js和java类型对应关系
     * @param name
     * @return
     */
    public static String invokeFromJsFile(Object name){
        System.out.println(name.getClass());
        System.out.println("from js file "+name);
        return name+"---";
    }

    /**
     *在js调用java方法,传入ScriptObjectMirror
     * @param mirror
     */
    public static void invokeFromJsFileMirror(ScriptObjectMirror mirror){
        System.out.println(mirror.getClassName() + ": " +
                Arrays.toString(mirror.getOwnKeys(true)));

    }

    public static void invokeFromJsFileJSObject(ScriptObjectMirror person){
        System.out.println(person.callMember("getFullName"));

    }

    public static void main(String[] args) {
        NashornTest1 nashornTest1 = ObjectFactory.create(NashornTest1::new);
//        nashornTest1.sayHelloByNashorn();
//        nashornTest1.testNashornFromJsFile();
        nashornTest1.testNashornFromJsFileExtensions();
    }

}
