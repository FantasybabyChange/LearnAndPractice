package com.fantasybaby.basic.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**使用atomicReference保证在增加时的数据原子性
 * @author liuxi
 * @date2018年04月11日 19:54
 */
public class AtomicReferenceStampedTest {
    public AtomicStampedReference<Integer> atint = new AtomicStampedReference<>(30,0);

    public class vendor implements Runnable{
        @Override
        public void run() {
            while(true){
                Integer reference = atint.getReference();
                int stamp = atint.getStamp();
                if(reference < 20){
                    if(atint.compareAndSet(reference, stamp + 20, reference, reference + 1)){
                        System.out.println("充值成功,余额是:"+atint.getReference());
                    }else{
                        System.out.println("充值失败,余额是:"+reference);
                    }
                }
            }
        }
    }
    public class customer implements Runnable{
        @Override
        public void run() {
            while(true){
                Integer reference = atint.getReference();
                int stamp = atint.getStamp();
                if(reference > 10){
                    if(atint.compareAndSet(reference,reference-10,stamp,stamp)){
                        System.out.println("消费10元成功 "+atint.getReference());
                    }else{
                        System.out.println("消费10元失败 "+reference );
                    }
                }else{
                    System.out.println("余额不足 :"+reference);
                }
            }
        }
    }

    public void startLogic(){
        Thread th = new Thread(new AtomicReferenceStampedTest.vendor());
    }

    public static void main(String[] args) throws InterruptedException {
        //Thread th = new Thread(new AtomicReferenceStampedTest().)
        //customer customer = new customer();
    }

}
