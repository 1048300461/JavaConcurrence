package com.example.javaconcurrence.ThreadPool;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolLearn {
    //线程池的优势
        //①降低系统资源消耗，通过重用已存在的线程，降低线程创建和销毁造成的消耗；
        //②提高系统响应速度，当有任务到达时，无需等待新线程的创建便能立即执行；

        //③方便线程并发数的管控，线程若是无限制的创建，不仅会额外消耗大量系统资
        //源，更是占用过多资源而阻塞系统或oom等状况，从而降低系统的稳定性。线程池
        //能有效管控线程，统一分配、调优，提供资源使用率；

        //④更强大的功能，线程池提供了定时、定期以及可控线程数等功能的线程池，使用
        //方便简单。

    //ThreadPoolExecutor构造函数中参数含义
        //1 corePoolSize
            //线程池中的核心线程数，默认情况下，核心线程一直存活在线程池中，即便他们在
            //线程池中处于闲置状态。除非我们将ThreadPoolExecutor的
            //allowCoreThreadTimeOut属性设为true的时候，这时候处于闲置的核心线程在等待
            //新任务到来时会有超时策略，这个超时时间由keepAliveTime来指定。一旦超过所
            //设置的超时时间，闲置的核心线程就会被终止。
        //2 maximumPoolSize
            //线程池中所容纳的最大线程数，如果活动的线程达到这个数值以后，后续的新任务
            //将会被阻塞。包含核心线程数+非核心线程数。
        //3 keepAliveTime
            //非核心线程闲置时的超时时长，对于非核心线程，闲置时间超过这个时间，非核心
            //线程就会被回收。只有对ThreadPoolExecutor的allowCoreThreadTimeOut属性设
            //为true的时候，这个超时时间才会对核心线程产生效果。
        //4 unit
            //用于指定keepAliveTime参数的时间单位。他是一个枚举，可以使用的单位有天
            //（TimeUnit.DAYS），小时（TimeUnit.HOURS），分钟
            //（TimeUnit.MINUTES），毫秒(TimeUnit.MILLISECONDS)，微秒
            //(TimeUnit.MICROSECONDS, 千分之一毫秒)和毫微秒
            //(TimeUnit.NANOSECONDS, 千分之一微秒);
        //5 workQueue
            //线程池中保存等待执行的任务的阻塞队列。通过线程池中的execute方法提交的
            //Runable对象都会存储在该队列中。我们可以选择下面几个阻塞队列
                //阻塞队列 说明
                //ArrayBlockingQueue 基于数组实现的有界的阻塞队列,该队列按照
                //FIFO（先进先出）原则对队列中的元素进行排序。
                //LinkedBlockingQueue 基于链表实现的阻塞队列，该队列按照FIFO（先进先
                //出）原则对队列中的元素进行排序。
                //SynchronousQueue
                //内部没有任何容量的阻塞队列。在它内部没有任何的
                //缓存空间。对于SynchronousQueue中的数据元素只
                //有当我们试着取走的时候才可能存在。
                //PriorityBlockingQueue 具有优先级的无限阻塞队列。
            //或者通过自定义阻塞队列
        //6 threadFactory
            //线程工厂，为线程池提供新线程的创建。ThreadFactory是一个接口，里面只有一
            //个newThread方法。 默认为DefaultThreadFactory类。
        //7 handler
            //是RejectedExecutionHandler对象，而RejectedExecutionHandler是一个接口，里
            //面只有一个rejectedExecution方法。当任务队列已满并且线程池中的活动线程已经
            //达到所限定的最大值或者是无法成功执行任务，这时候ThreadPoolExecutor会调
            //用RejectedExecutionHandler中的rejectedExecution方法。在
            //ThreadPoolExecutor中有四个内部类实现了RejectedExecutionHandler接口。在线
            //程池中它默认是AbortPolicy，在无法处理新任务时抛出
            //RejectedExecutionException异常。
            //下面是在ThreadPoolExecutor中提供的四个可选值。
            //可选值 说明
            //CallerRunsPolicy 只用调用者所在线程来运行任务。
            //AbortPolicy 直接抛出RejectedExecutionException异常。
            //DiscardPolicy 丢弃掉该任务，不进行处理。
            //DiscardOldestPolicy 丢弃队列里最近的一个任务，并执行当前任务。
            //我们也可以通过实现RejectedExecutionHandler接口来自定义我们自己的handler。
            //如记录日志或持久化不能处理的任务。

    //四种线程池类
        //ExecutorService service = Executors.类别(参数);
        //1 Executors.newFixedThreadPool(nThreads)：只有核心线程，能够更快速的响应外界请求
        //2 Executors.newCachedThreadPool()：在线程池中如果现有线程无法接收任务,将会创建新的线程来执行任务。
        //3 Executors.newScheduledThreadPool(corePoolSize)：核心线程数是固定的，对于非核心线程几乎可以说是没有限制的，并且当非核
        //心线程处于限制状态的时候就会立即被回收。适用于定时执行的任务
        //4 Executors.newSingleThreadExecutor()：将所有的外界任务统一到一个线程中支持，所以在这个
        //任务执行之间我们不需要处理线程同步的问题。

    //线程池的使用技巧：不同的任务类别应采用不同规模的线程池，任务类
    //别可划分为CPU密集型任务、IO密集型任务和混合型任务。(N代表CPU个数)
        //1 CPU密集型任务：线程池中线程个数应尽量少，如配置N+1个线程的线程池。
        //2 IO密集型任务：由于IO操作速度远低于CPU速度，那么在运行这类任务时，CPU绝大
        //多数时间处于空闲状态，那么线程池可以配置尽量多些的线程，以提高
        //CPU利用率，如2*N。
        //3 混合型任务：可以拆分为CPU密集型任务和IO密集型任务，当这两类任务执行时间相
        //差无几时，通过拆分再执行的吞吐率高于串行执行的吞吐率，但若这两
        //类任务执行时间有数据级的差距，那么没有拆分的意义。

    public static void main(String[] args) {
        //创建线程池
        ExecutorService service = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        //可以通过一下两种向线程池提交任务
        //execute：无返回值，无法得知任务是否被线程池执行成功：
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("execute方式");
            }
        });
        //submit：当我们使用submit来提交任务时,它会返回一个future,我们就可以通过这个future来
        //判断任务是否执行成功，还可以通过future的get方法来获取返回值。如果子线程任
        //务没有完成，get方法会阻塞住直到任务完成，而使用get(long timeout, TimeUnit
        //unit)方法则会阻塞一段时间后立即返回，这时候有可能任务并没有执行完。
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Submit方式");
                return 2;
            }
        });

        try{
            Integer number = future.get();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }

        //线程池的关闭
        //shutdown()：将线程池状态设置成SHUTDOWN状态，然后中断所有没有正在执
        //行任务的线程。
        //shutdownNow()：将线程池的状态设置成STOP状态，然后中断所有任务(包括正
        //在执行的)的线程，并返回等待执行任务的列表。
        //中断采用interrupt方法，所以无法响应中断的任务可能永远无法终止。 但调用上述
        //的两个关闭之一，isShutdown()方法返回值为true，当所有任务都已关闭，表示线
        //程池关闭完成，则isTerminated()方法返回值为true。当需要立刻中断所有的线程，
        //不一定需要执行完任务，可直接调用shutdownNow()方法。
        service.shutdownNow();
    }




}
