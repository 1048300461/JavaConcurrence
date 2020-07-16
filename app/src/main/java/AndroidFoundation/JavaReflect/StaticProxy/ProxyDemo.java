package AndroidFoundation.JavaReflect.StaticProxy;

public class ProxyDemo {
    public static void main(String[] args) {
        //代理模式的好处：
        //假如有这样的需求，要在某些模块方法调用前后加上一些统一的前后处理操作，比
        //如在添加购物车、修改订单等操作前后统一加上登陆验证与日志记录处理，该怎样
        //实现？首先想到最简单的就是直接修改源码，在对应模块的对应方法前后添加操
        //作。如果模块很多，你会发现，修改源码不仅非常麻烦、难以维护，而且会使代码
        //显得十分臃肿。
        //这时候就轮到代理模式上场了，它可以在被调用方法前后加上自己的操作，而不需
        //要更改被调用类的源码，大大地降低了模块之间的耦合性，体现了极大的优势。
        RealSubject subject = new RealSubject();
        Proxy p = new Proxy(subject);

        p.request();
    }
}
