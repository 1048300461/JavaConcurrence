package JavaConcurrence.ProducerConsumer;

public class ThreadSubtract extends Thread{
    private Subtract rSubtract;

    public ThreadSubtract(Subtract rSubtract){
        super();
        this.rSubtract = rSubtract;
    }

    @Override
    public void run() {
        rSubtract.subtract();
    }
}
