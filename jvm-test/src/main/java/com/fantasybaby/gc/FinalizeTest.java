package com.fantasybaby.gc;

/**
 * @author: liuxi
 * @time: 2018/7/30 16:11
 */
public class FinalizeTest {
    public static FinalizeTest obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("call finalize");
        obj = this;

    }
    public static void main(String[] args) throws
            InterruptedException{
        obj=new FinalizeTest();
        obj=null;   //可复活
        System.gc();
        Thread.sleep(1000);
        if(obj==null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
        System.out.println("第二次gc");
        obj=null;    //不可复活
        System.gc();
        Thread.sleep(1000);
        if(obj==null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
    }

}
