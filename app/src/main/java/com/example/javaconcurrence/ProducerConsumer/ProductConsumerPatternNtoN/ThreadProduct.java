package com.example.javaconcurrence.ProducerConsumer.ProductConsumerPatternNtoN;

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
