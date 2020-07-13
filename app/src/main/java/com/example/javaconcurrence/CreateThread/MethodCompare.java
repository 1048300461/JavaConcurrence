package com.example.javaconcurrence.CreateThread;

public class MethodCompare {
    //创建线程的三种方式的对比
//    采用实现Runnable、Callable接口的方式创见多线程时，优势是：
//    线程类只是实现了Runnable接口或Callable接口，还可以继承其他类。
//    在这种方式下，多个线程可以共享同一个target对象，所以非常适合多个相同线程
//    来处理同一份资源的情况，从而可以将CPU、代码和数据分开，形成清晰的模型，
//    较好地体现了面向对象的思想。
//    劣势是：
//    编程稍微复杂，如果要访问当前线程，则必须使用Thread.currentThread()方法。
//    使用继承Thread类的方式创建多线程时优势是：
//    编写简单，如果需要访问当前线程，则无需使用Thread.currentThread()方法，直接
//    使用this即可获得当前线程。
//    劣势是：
//    线程类已经继承了Thread类，所以不能再继承其他父类。
}
