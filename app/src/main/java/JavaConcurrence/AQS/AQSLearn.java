package JavaConcurrence.AQS;

public class AQSLearn {
    //AQS:AbstractQueuedSynchronizer提供了一个基于FIFO队列，可以用于构建锁或者其他
    //相关同步装置的基础框架。该同步器（以下简称同步器）利用了一个int来表示状
    //态，期望它能够成为实现大部分同步需求的基础。

    //同步器与锁
        //同步器：同步器面向的是线程访问和资源控制，它定义了线程对资源是否能够获取以及线程的排队等操作。
        //锁：锁的API是面向使用者的，它定义了与锁交互的公共行为，而
        //每个锁需要完成特定的操作也是透过这些行为来完成的（比如：可以允许两个线程
        //进行加锁，排除两个以上的线程），但是实现是依托给同步器来完成。

}
