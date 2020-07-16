package JavaConcurrence.ProducerConsumer;

//进行减法操作，执行完后进入等待状态，等待Add类唤醒notify
public class Subtract {
    private String lock;

    public Subtract(String lock){
        super();
        this.lock = lock;
    }

    public void subtract(){
        try{
            synchronized (lock){
                //可能会出错，解决方法，将if改为while
                while(ValueObject.list.size() == 0){
                    System.out.println("wait begin TreadName=" +
                            Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end ThreadName=" +
                            Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size =" + ValueObject.list.size());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
