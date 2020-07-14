package com.example.javaconcurrence.ProducerConsumer.ProductConsumerPattern1to1;

//生产者线程
public class ThreadProduct extends Thread{
    private Product product;

    public ThreadProduct(Product product){
        super();
        this.product = product;
    }

    @Override
    public void run() {
        //死循环，不断的生产
        while (true){
            product.setValue();
        }
    }
}
