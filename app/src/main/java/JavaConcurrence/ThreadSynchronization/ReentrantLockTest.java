package JavaConcurrence.ThreadSynchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 重入锁
public class ReentrantLockTest {
     private Lock lock = new ReentrantLock();
     public void method1(){
         lock.lock();
         try{
             System.out.println("方法1获得ReentrantLock锁运行了");
             method2();
         }finally {
             lock.unlock();
         }
     }

    private void method2() {
         lock.lock();
         try{
             System.out.println("方法1里面调用的方法2重入ReentrantLock锁，也正常运行了");
         }finally {
             lock.unlock();
         }
    }

    public static void main(String[] args) {
        new ReentrantLockTest().method1();
    }
}
