package com.fantasybaby.asm;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * 覆盖原有类
 */
public class Generator {
    public static void main(String args[]) throws Exception {
        ClassReader cr = new ClassReader("com.fantasybaby.asm.AopDemo");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
        AopAsm classAdapter = new AopAsm(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        URL resource = Generator.class.getResource("AopDemo.class");
        String path = resource.getPath();
        System.out.println(path);
        File file = new File(path);
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
        new AopDemo().fantasybaby();
    }

}
