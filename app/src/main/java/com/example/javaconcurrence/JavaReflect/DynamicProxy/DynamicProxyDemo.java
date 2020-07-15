package com.example.javaconcurrence.JavaReflect.DynamicProxy;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        //我们通过newProxyInstance就产生了一个Subject 的实例，即代理类的
        //实例，然后就可以通过Subject .request()，就会调用InvocationHandler中的
        //invoke()方法，传入方法Method对象，以及调用方法的参数，通过Method.invoke调
        //用RealSubject中的方法的request()方法。同时可以在InvocationHandler中的
        //invoke()方法加入其他执行逻辑。

        //1 创建目标对象
        RealSubject realSubject = new RealSubject();

        //创建调用处理器对象
        ProxyHandler handler = new ProxyHandler(realSubject);

        //动态生成代理对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);
        proxySubject.request();

    }
}
