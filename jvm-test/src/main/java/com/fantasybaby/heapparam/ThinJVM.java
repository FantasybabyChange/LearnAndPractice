package com.fantasybaby.heapparam;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 1、把程序运行所需要的class文件通过-XX:TraceClassLoading打印到文本文件
 *2、用自己写的程序把需要的class和rt路径，精简rt存放的路径设置好
 *3、然后将rt1里面的目录和文件打包成rt.zip,改名为rt.jar，然后替换原来的rt.jar
 *4、可以达到精简的作用，再将Java.exe和对应的dll copy到相应的目录，
 *5、写一个批处理命令，用于自带的Java去执行jar包。
 * 通过命令使java虚拟机瘦身
 */
public class ThinJVM {
    private String needClazz = "d:\\needClazz.txt";//需要的class
    private String rtPath = "D:\\Program Files\\Java\\jre6\\lib";//rt存放路径
    private String dstRtPath = "D:/cutJar/";//精简后的路径
    private JarFile rtJar;

    public static void main(String[] args) throws Exception {
        ThinJVM cutJre = new ThinJVM();
        cutJre.rtJar = new JarFile(cutJre.rtPath + "\\rt.jar");
//        cutJre.copyClass("[Loaded sun.reflect.FieldAccessor from sda]");
		 cutJre.execute();
    }

    private void execute() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(needClazz)));
        String string = br.readLine();
        while (string != null) {
            string = br.readLine();
        }
        copyClass(string);
    }

    private boolean copyClass(String traceStr) throws IOException {
        if (traceStr.startsWith("[Loaded")) {
            String className = traceStr.split(" ")[1];
            //不是rt里面的Jar包，是自己有的
            if(className.contains("zh")){
                return true;
            }
            copyFile(className);
        }
        return false;
    }

    private void copyFile(String className) throws IOException {
        String classFile = className.replace(".", "/") + ".class";
        String classDir = classFile.substring(0,classFile.lastIndexOf("/"));

        File dir=new File(dstRtPath+classDir);
        System.out.println(dir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        JarEntry jarEntry = rtJar.getJarEntry(classFile);
        InputStream ins = rtJar.getInputStream(jarEntry);
        File file = new File(dstRtPath+ classFile);
        System.out.println(file);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        IOUtils.copy(ins, fos);
        ins.close();
        fos.close();

    }
}