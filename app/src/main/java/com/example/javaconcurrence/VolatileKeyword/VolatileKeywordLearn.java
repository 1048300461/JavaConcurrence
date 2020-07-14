package com.example.javaconcurrence.VolatileKeyword;

public class VolatileKeywordLearn {
    //并发编程三大概念
    //1 原子性：即一个操作或者多个操作，要么全部执行，并且执行的过程不会被任何因素打断，要么就都不执行。
    //2 可见性：可见性是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
    //3 有序性：即程序执行的顺序按照代码的先后顺序执行。

    //要想并发程序正确地执行，必须要保证原子性、可见性以及有序性。只要有一个没有被保证，就有可能会导致程序运行不正确。

    //指令重排序：处理器为了提高程序运行效率，可能
    //会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序
    //一致，但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。

    //先行发生原则
    //①程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写
    //在后面的操作
    //②锁定规则：一个unLock操作先行发生于后面对同一个锁的lock操作
    //③volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
    //④传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以
    //得出操作A先行发生于操作C
    //⑤线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作
    //⑥线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到
    //中断事件的发生
    //⑦线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过
    //Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
    //⑧对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始

    //volatile关键字：被volatile修饰后1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量
    //的值，这新值对其他线程来说是立即可见的。2）禁止进行指令重排序。

}