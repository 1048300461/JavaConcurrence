package JavaFoundation.JavaReflect.StaticProxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("request");
    }
}
