package com.fantasybaby.heapparam;

import com.fantasybaby.domain.WordDO;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**-XX:MetaspaceSize
 * -XX:MaxMetaspaceSize
 * -XX:-UseCompressedClassPointers
 * @author: liuxi
 * @time: 2018/7/25 10:38
 */
public class MethodAreaTest extends  ClassLoader{
    /*public void MetaSpaceOOM() {
        // 类持有
        List<Class<?>> classes = new ArrayList<Class<?>>();
        // 循环1000w次生成1000w个不同的类。
        for (int i = 0; i < 10000000; ++i) {
            ClassWriter cw = new ClassWriter(0);
            // 定义一个类名称为Class{i}，它的访问域为public，父类为java.lang.Object，不实现任何接口
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null,
                    "java/lang/Object", null);
            // 定义构造函数<init>方法
            MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                    "()V", null, null);
            // 第一个指令为加载this
            mw.visitVarInsn(Opcodes.ALOAD, 0);
            // 第二个指令为调用父类Object的构造函数
            mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
                    "<init>", "()V");
            // 第三条指令为return
            mw.visitInsn(Opcodes.RETURN);
            mw.visitMaxs(1, 1);
            mw.visitEnd();

            MethodAreaTest test = new MethodAreaTest();
            byte[] code = cw.toByteArray();
            // 定义类
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
    }*/
    /**
     * 通过cgLib造成metaSpace溢出
     * @throws InterruptedException
     */
    public void test2() throws InterruptedException {

        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(WordDO.class);
            enhancer.setUseCache(false);// 关闭CGLib缓存，否则总是生成同一个类
            enhancer.setCallback((MethodInterceptor) (obj, method, args, methodproxy) -> {
                // TODO Auto-generated method stub
                return methodproxy.invokeSuper(obj,args);
            });
            enhancer.create();

            Thread.sleep(100);
        }
    }
    public static void main(String[] args) throws InterruptedException {

    }
}