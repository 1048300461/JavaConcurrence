package JavaConcurrence.ProducerConsumer.ProductConsumerPatternNtoN;

import JavaConcurrence.ProducerConsumer.ProductConsumerPattern1to1.StringObject;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//消费者
public class Consumer {
    private ReentrantLock lock;
    private Condition condition;

    public Consumer(ReentrantLock lock, Condition condition){
        super();
        this.lock = lock;
        this.condition = condition;
    }
    public void getValue(){
        try {
            lock.lock();
            while (StringObject.value.equals("")){
                condition.await();
            }
            System.out.println("get的值是：" + StringObject.value);
            StringObject.value = "";
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
