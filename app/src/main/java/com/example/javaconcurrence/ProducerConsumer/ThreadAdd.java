package com.example.javaconcurrence.ProducerConsumer;

public class ThreadAdd extends Thread{
    private Add pAdd;

    public ThreadAdd(Add pAdd){
        super();
        this.pAdd = pAdd;
    }

    @Override
    public void run() {
        pAdd.add();
    }
}
