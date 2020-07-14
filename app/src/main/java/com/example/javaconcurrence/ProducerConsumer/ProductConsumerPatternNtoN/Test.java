package com.example.javaconcurrence.ProducerConsumer.ProductConsumerPatternNtoN;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition newCondition = lock.newCondition();
        Product product = new Product(lock, newCondition);
        Consumer consumer = new Consumer(lock, newCondition);
        for(int i = 0; i < 3; i++){
            ThreadProduct pThread = new ThreadProduct(product);
            ThreadConsumer cThread = new ThreadConsumer(consumer);

            pThread.start();
            cThread.start();
        }
    }
}
