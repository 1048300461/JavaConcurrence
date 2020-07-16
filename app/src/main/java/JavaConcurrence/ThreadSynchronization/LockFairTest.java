package JavaConcurrence.ThreadSynchronization;

import java.util.concurrent.locks.ReentrantLock;

//公平锁
public class LockFairTest implements Runnable{
    //创建公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockFairTest lockFairTest = new LockFairTest();
        Thread th1=new Thread(lockFairTest);
        Thread th2=new Thread(lockFairTest);
        th1.start();
        th2.start();

    }


}
