package com.example.javaconcurrence.ProducerConsumer.ProductConsumerPatternNtoN;

public class ThreadConsumer extends Thread{
    private Consumer consumer;

    public ThreadConsumer(Consumer consumer){
        super();
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true){
            consumer.getValue();
        }
    }
}
