package com.example.javaconcurrence.ThreadSynchronization;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSynchronizationLearn {
    //实现线程同步问题一共有两种锁
    //synchronized：实现同步的基础：Java中每个对象都可以作为锁。当线程试图访
    //问同步代码时，必须先获得对象锁，退出或抛出异常时必须释放锁。
    //synchronized：代码块同步 和 方法同步。
        //JVM基于进入和退出Monitor对象来实现代码块同步和方法同步，两者实现细节不同。
        //代码块同步：在编译后通过将 monitorenter 指令插入到同步代码块的开始处，
        //将 monitorexit 指令插入到方法结束处和异常处，通过反编译字节码可以观察
        //到。
        //方法同步：synchronized方法在 method_info结构 有 ACC_synchronized 标
        //记，线程执行时会识别该标记，获取对应的锁，实现方法同步。
    //synchronized的使用场景
        //1 方法同步：锁住的是类的其中一个实例，，但如果不同线程同时对
        //该类的不同对象执行这个同步方法时，则线程之间不会形成互斥，因为他们拥有的
        //是不同的锁。
        //public synchronized void metho
        //2 代码块同步：同上
        //synchronized(this){ ... }
        //3 方法同步：锁住的是该类，当所有该类的对象(多个对象)在不同线程中调用这个static同步方法
        //时，线程之间会形成互斥，达到同步效果。
        //public synchronized static void method3
        //4 代码块同步：同上
        //synchronized(Test.class){ ... }
        //5 代码块同步：这里面的o可以是一个任何Object对象或数组，并不一定是它本身对象或者类，谁
        //拥有o这个锁，谁就能够操作该块程序代码。
        //synchronized(o) {}

    //ReentrantLock：一个可重入的互斥锁，它具有与使用synchronized方法和语句所访
    //问的隐式监视器锁相同的一些基本行为和语义，但功能更强大。
    //Lock接口：它提供了与 synchronized 关键字类似
    //的同步功能，只是在使用时需要显式地获取和释放锁，缺点就是缺少
    //像 synchronized 那样隐式获取释放锁的便捷性，但是却拥有了锁获取与释放的
    //可操作性，可中断的获取锁以及超时获取锁等多种 synchronized 关键字所不具
    //备的同步特性。
    //        ReentrantLock lock = new ReentrantLock(); // 参数默认false，不公平锁
    //        lock.lock(); // 如果被其它资源锁定，会在此等待锁释放，达到暂停的效果
    //        try {
    //        //操作
    //        } finally {
    //            lock.unlock(); //释放锁
    //        }

    //重入锁：当一个线程得到一个对象后，再次请求该对象锁时是可以再次得到该对象的锁的。
    //具体概念就是：自己可以再次获取自己的内部锁。
    //Java里面内置锁(synchronized)和Lock(ReentrantLock)都是可重入的。

    //公平锁：ReentrantLock是一种公平锁，在构造方法中传入true就是公平锁，传入false就是非公平锁


    //synchronized和ReentrantLock的比较
    //1.区别：
    //1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的
    //语言实现；
    //2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现
    //象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造
    //成死锁现象，因此使用Lock时需要在finally块中释放锁；
    //3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用
    //synchronized时，等待的线程会一直等待下去，不能够响应中断；
    //4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
    //5）Lock可以提高多个线程进行读操作的效率。
    //总结：ReentrantLock相比synchronized，增加了一些高级的功能。但也有一定缺陷。
    //2.两者在锁的相关概念上区别：
    //1)可中断锁
    //顾名思义，就是可以响应中断的锁。
    //在Java中，synchronized就不是可中断锁，而Lock是可中断锁。如果某一线程A
    //正在执行锁中的代码，另一线程B正在等待获取该锁，可能由于等待时间过长，线
    //程B不想等待了，想先处理其他事情，我们可以让它中断自己或者在别的线程中中
    //断它，这种就是可中断锁。
    //lockInterruptibly() 的用法体现了Lock的可中断性。
    //2)公平锁
    //公平锁即尽量以请求锁的顺序来获取锁。比如同是有多个线程在等待一个锁，当这
    //个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该锁（并不是绝对
    //的，大体上是这种顺序），这种就是公平锁。
    //非公平锁即无法保证锁的获取是按照请求锁的顺序进行的。这样就可能导致某个或
    //者一些线程永远获取不到锁。
    //在Java中，synchronized就是非公平锁，它无法保证等待的线程获取锁的顺序。
    //ReentrantLock可以设置成公平锁。
    //3)读写锁
    //读写锁将对一个资源（比如文件）的访问分成了2个锁，一个读锁和一个写锁。
    //正因为有了读写锁，才使得多个线程之间的读操作可以并发进行，不需要同步，而
    //写操作需要同步进行，提高了效率。
    //ReadWriteLock就是读写锁，它是一个接口，ReentrantReadWriteLock实现了这个
    //接口。
    //可以通过readLock()获取读锁，通过writeLock()获取写锁。
    //4)绑定多个条件
    //一个ReentrantLock对象可以同时绑定多个Condition对象，而在synchronized中，
    //锁对象的wait()和notify()或notifyAll()方法可以实现一个隐含的条件，如果要和多余
    //一个条件关联的时候，就不得不额外地添加一个锁，而ReentrantLock则无须这么
    //做，只需要多次调用new Condition()方法即可。
    //3.性能比较
    //在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而 当竞争资源非常
    //激烈时（即有大量线程同时竞争），此时ReentrantLock的性能要远远优于
    //synchronized 。所以说，在具体使用时要根据适当情况选择。
    //在JDK1.5中，synchronized是性能低效的。因为这是一个重量级操作，它对性能最
    //大的影响是阻塞的是实现，挂起线程和恢复线程的操作都需要转入内核态中完成，
    //这些操作给系统的并发性带来了很大的压力。相比之下使用Java提供的
    //ReentrankLock对象，性能更高一些。到了JDK1.6，发生了变化，对synchronize加
    //入了很多优化措施，有自适应自旋，锁消除，锁粗化，轻量级锁，偏向锁等等。导
    //致在JDK1.6上synchronize的性能并不比Lock差。官方也表示，他们也更支持
    //synchronize，在未来的版本中还有优化余地，所以还是提倡在synchronized能实现
    //需求的情况下，优先考虑使用synchronized来进行同步。

}
