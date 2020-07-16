package AndroidFoundation.JavaReflect.StaticProxy;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("PreProcess");
        subject.request();
        System.out.println("PostProcess");
    }
}
