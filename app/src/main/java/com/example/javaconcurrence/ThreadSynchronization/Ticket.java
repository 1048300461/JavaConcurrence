package com.example.javaconcurrence.ThreadSynchronization;

import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    //当前拥有的票数
    private int num = 100;

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        //存在线程同步问题
        /*while(true){
            if(num > 0){
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){

                }

                System.out.println(Thread.currentThread().getName() + "sale" + num--);
            }
        }*/

        //使用synchronized解决同步问题
        /*while(true){

            try{
                Thread.sleep(10);
            }catch (InterruptedException e){

            }
            synchronized (this){
                if(num > 0){
                    System.out.println(Thread.currentThread().getName() + "sale" + num--);
                }
            }
        }*/

        //使用ReentrantLock解决线程同步问题
        while(true){
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
            }

            lock.lock();

            if(num > 0){
                System.out.println(Thread.currentThread().getName() + "sale" + num--);
            }
            lock.unlock();
        }



    }

    public static void main(String[] args) {
        Ticket t = new Ticket();//创建一个线程任务对象。
        //创建4个线程同时卖票
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        //启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
