package JavaConcurrence.ProducerConsumer;

//生产者/消费者模式
public class ProducerConsumerLearn {
    //线程间通信的两种方式
    //1.wait()和notify()：属于Object的final方法，被所有类继承，不能修改
        //wait()方法：让当前线程进入等待，并释放锁
        //wait(long)方法：让当前线程进入等待，并释放锁，不过等待时间为long，超过
        //这个时间没有对当前线程进行唤醒，将自动唤醒。
        //notify()方法：让当前线程通知那些处于等待状态的线程，当前线程执行完毕后
        //释放锁，并从其他线程中唤醒其中一个继续执行。
        //notifyAll()方法：让当前线程通知那些处于等待状态的线程，当前线程执行完毕
        //后释放锁，将唤醒所有等待状态的线程。

        //wait()方法使用注意：
        //①当前的线程必须拥有当前对象的monitor，也即lock，就是锁，才能调用wait()方
        //法，否则将抛出异常java.lang.IllegalMonitorStateException。
        //②线程调用wait()方法，释放它对锁的拥有权，然后等待另外的线程来通知它（通知
        //的方式是notify()或者notifyAll()方法），这样它才能重新获得锁的拥有权和恢复执
        //行。
        //③要确保调用wait()方法的时候拥有锁，即，wait()方法的调用必须放在
        //synchronized方法或synchronized块中。

        //wait()与sleep()比较：wait()会释放掉对象的锁，Thread.Sleep()不会释放掉对象的锁

        //notify()方法使用注意：
        //①如果多个线程在等待，它们中的一个将会选择被唤醒。这种选择是随意的，和具
        //体实现有关。（线程等待一个对象的锁是由于调用了wait()方法）。
        //②被唤醒的线程是不能被执行的，需要等到当前线程放弃这个对象的锁，当前线程
        //会在方法执行完毕后释放锁。

        //wait()/notify()协作的两个注意事项：
            //1 通知过早    2 等待wait的条件发生变化，这是线程间协作中经常出现的一种情况，需要避免

    //2 Condition实现等待/通知
    //关键字synchronized与wait()和notify()/notifyAll()方法相结合可以实现等待/通知模
    //式，类似ReentrantLock也可以实现同样的功能，但需要借助于Condition对象。
}
