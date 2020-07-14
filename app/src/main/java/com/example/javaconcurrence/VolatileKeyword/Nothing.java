package com.example.javaconcurrence.VolatileKeyword;

//volatile不能确保原子性
public class Nothing {
    //假如某个时刻变量inc的值为10，线程1对变量进行自增操作，线程1先读取了变量
    //inc的原始值，然后线程1被阻塞了；
    //然后线程2对变量进行自增操作，线程2也去读取变量inc的原始值，由于线程1只是
    //对变量inc进行读取操作，而没有对变量进行修改操作，所以不会导致线程2的工作
    //内存中缓存变量inc的缓存行无效，也不会导致主存中的值刷新， 所以线程2会直接
    //去主存读取inc的值，发现inc的值时10，然后进行加1操作，并把11写入工作内存，
    //最后写入主存。
    //然后线程1接着进行加1操作，由于已经读取了inc的值，注意此时在线程1的工作内
    //存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的值为11，然后将11写入
    //工作内存，最后写入主存。那么两个线程分别进行了一次自增操作后，inc只增加了1。
    //根源就在这里，自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的。
    private volatile int inc = 0;
    private volatile static int count = 10;

    private void increase(){
        ++inc;
    }

    public static void main(String[] args) {
        int loop = 10;
        Nothing nothing = new Nothing();
        while (loop-- > 0){
            nothing.operation();
        }
    }

    private void operation() {
        final Nothing test = new Nothing();
        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000000; j++) {
                        test.increase();
                    }
                    --count;
                }
            }).start();
        }
        while (count > 0){}
        System.out.println("最后的数据为：" + test.inc);
    }
}
