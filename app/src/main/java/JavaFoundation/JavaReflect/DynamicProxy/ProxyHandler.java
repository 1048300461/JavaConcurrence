package JavaFoundation.JavaReflect.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private Subject subject;
    public ProxyHandler(Subject subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before");
        //调用RealSubject中的方法
        Object result = method.invoke(subject, objects);
        System.out.println("after");
        return result;
    }
}
