package JavaConcurrence.ProducerConsumer.ProductConsumerPatternNtoN;

import JavaConcurrence.ProducerConsumer.ProductConsumerPattern1to1.StringObject;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//Condition方式实现生产者
public class Product {
    private ReentrantLock lock;
    private Condition condition;

    public Product(ReentrantLock lock, Condition condition){
        super();
        this.lock = lock;
        this.condition = condition;
    }

    public void setValue(){
        try {
            lock.lock();
            while (!StringObject.value.equals("")){
                condition.await();
            }
            String value = System.currentTimeMillis() + "" + System.nanoTime();
            System.out.println("set的值是：" + value);
            StringObject.value = value;
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
