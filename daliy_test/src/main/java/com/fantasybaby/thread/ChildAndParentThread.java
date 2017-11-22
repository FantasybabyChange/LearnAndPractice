package com.fantasybaby.thread;

public class ChildAndParentThread {



    public static void main(String[] args) {
        ParentThread parent = new ParentThread();
        new Thread(parent).start();
    }

}

class ChildThread implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("childThread -----");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ParentThread implements  Runnable{

    @Override
    public void run() {
        System.out.println("ParentThread -----");
        Thread childThread = new Thread(new ChildThread());
        //当设置为守护线程后　　会随着主线程的结束而结束
        childThread.setDaemon(true);
        childThread.start();
    }
}