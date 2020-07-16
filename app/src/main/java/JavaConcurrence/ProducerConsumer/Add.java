package JavaConcurrence.ProducerConsumer;

//执行加法操作，然后通知Subtract类
public class Add {
    private String lock;

    public Add(String lock){
        super();
        this.lock = lock;
    }

    public void add(){
        synchronized (lock){
            ValueObject.list.add("anything");
            lock.notifyAll();
        }
    }
}
