package JavaConcurrence.ProducerConsumer.ProductConsumerPattern1to1;

public class Test {
    public static void main(String[] args) {
        String lock = new String("");
        Product product = new Product(lock);
        Consumer consumer = new Consumer(lock);
        ThreadProduct pThread = new ThreadProduct(product);
        ThreadConsumer cThread = new ThreadConsumer(consumer);

        ThreadProduct pThread1 = new ThreadProduct(product);
        ThreadConsumer cThread2 = new ThreadConsumer(consumer);

        pThread.start();
        cThread.start();

        pThread1.start();
        cThread2.start();
    }
}
