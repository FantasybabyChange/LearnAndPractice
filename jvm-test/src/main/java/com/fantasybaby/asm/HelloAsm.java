package com.fantasybaby.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.lang.reflect.InvocationTargetException;

import static org.objectweb.asm.Opcodes.*;


/**
 * a demo for hello world
 * @author  fantasybaby
 */
public class HelloAsm extends  ClassLoader{
    public  void createAClass(){
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_8, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,  null);
        mw.visitVarInsn(ALOAD, 0);  //this 入栈
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
        mw.visitInsn(RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();
        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",  "([Ljava/lang/String;)V", null, null);
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out",  "Ljava/io/PrintStream;");
        mw.visitLdcInsn("Hello world!");
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",  "(Ljava/lang/String;)V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0,0);
        mw.visitEnd();
        byte[] code = cw.toByteArray();
        HelloAsm loader = new HelloAsm();
        Class exampleClass = loader
                .defineClass("Example", code, 0, code.length);
        try {
            exampleClass.getMethods()[0].invoke(null, new Object[] { null });

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HelloAsm().createAClass();
    }
}
