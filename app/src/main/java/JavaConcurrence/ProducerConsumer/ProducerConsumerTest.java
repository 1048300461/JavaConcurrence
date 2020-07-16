package JavaConcurrence.ProducerConsumer;

public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        ThreadSubtract subtractThread1 = new ThreadSubtract(subtract);
        subtractThread1.setName("subtractThread1");
        subtractThread1.start();

        ThreadSubtract subtractThread2 = new ThreadSubtract(subtract);
        subtractThread2.setName("subtractThread2");
        subtractThread2.start();
        Thread.sleep(1000);

        ThreadAdd addThread = new ThreadAdd(add);
        addThread.setName("addThread");
        addThread.start();

        //当第二个ThreadSubtract线程执行减法操作时，抛出下标越界异常。
        //原因分析：一开始两个ThreadSubtract线程等待状态，当ThreadAdd线程添加一
        //个元素并唤醒所有线程后，第一个ThreadSubtract线程接着原来的执行到的地点开
        //始继续执行，删除一个元素并输出集合大小。同样，第二个ThreadSubtract线程也
        //如此，可是此时集合中已经没有元素了，所以抛出异常。
        //生产者/消费者模式
        //244
        //解决办法：从等待状态被唤醒后，重新判断条件，看看是否扔需要进入等待状态，
        //不需要进入再进行下一步操作。即把if()判断，改成while()。
    }
}
