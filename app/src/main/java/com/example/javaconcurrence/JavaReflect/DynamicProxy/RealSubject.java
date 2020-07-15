package com.example.javaconcurrence.JavaReflect.DynamicProxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject Request");
    }
}
