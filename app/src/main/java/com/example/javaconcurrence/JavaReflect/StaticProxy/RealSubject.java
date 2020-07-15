package com.example.javaconcurrence.JavaReflect.StaticProxy;

import com.example.javaconcurrence.ProducerConsumer.Subtract;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("request");
    }
}
