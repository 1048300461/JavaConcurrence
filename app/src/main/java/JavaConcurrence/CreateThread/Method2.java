package JavaConcurrence.CreateThread;

//通过Runnable接口创建线程类
public class Method2 implements Runnable {
    //1 定义runnable接口的实现类，并重写该接口的run()方法，该run()方法的方法
    //体同样是该线程的执行体
    //2 创建Runnable实现类的实例，并依此实例作为Thread的target来创建Thread对
    // 象，该Thread对象才是真正的线程对象
    //3 调用线程对象的start()方法来启动该线程

    int i = 0;

    @Override
    public void run() {
        for(; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }

    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getContextClassLoader() + " : " + i);
            if(i == 20){
                Method2 method2 = new Method2();
                new Thread(method2, "新线程1").start();
                new Thread(method2, "新线程2").start();
            }
        }
    }
}
