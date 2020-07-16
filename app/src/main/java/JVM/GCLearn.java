package JVM;

public class GCLearn {
    // 垃圾回收算法
        //1 标记-清楚算法
            //①首先标记出所有需要回收的对象
            //②在标记完成后统一回收所有被标记的对象。
        //2 复制算法
            //将可用内存按容量大小划分为大小相等的两块，每次只使用其中的一块。当一块内
            //存使用完了，就将还存活着的对象复制到另一块上面，然后再把已使用过的内存空
            //间一次清理掉。这样使得每次都是对整个半区进行内存回收，内存分配时也就不用
            //考虑内存碎片等复杂情况。
        //3 标记-整理算法
            //标记过程仍然与”标记-清除“算法一样，但后续步骤不是直接对可回收对象进行清
            //理，而是让所有存活的对象都向一端移动，然后直接清理掉边界以外的内存。

        //4 分代收集算法
            //一般是把Java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当
            //的收集算法。
            //在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法。
            //在老年代中，因为对象存活率高、没有额外空间对它进行分配担保，就必须采用“标
            //记-清除”或“标记-整理”算法来进行回收。

    // Minor GC和Full GC的区别
        //Minor GC:指发生在新生代的垃圾收集动作，该动作非常频繁。
        //Full GC/Major GC:指发生在老年代的垃圾收集动作，出现了Major GC，经常会伴
        //随至少一次的Minor GC。Major GC的速度一般会比Minor GC慢10倍以上。

    // 垃圾收集器
        //1 Serial：是最基本、发展历史最悠久的收集器。这是一个单线程收集器。但它的“单线程”的
        //意义并不仅仅说明它只会使用一个CPU或一条收集线程去完成垃圾收集工作，更重
        //要的是它在进行垃圾收集时，必须暂停其他所有的工作线程，直到它收集结束。是虚拟机运行在Client模式下的默认新生代收集器。

        //2 ParNew收集器：ParNew收集器其实就是Serial收集器的多线程版本。
        //是许多运行在Server模式下的虚拟机中首选的新生代收集器，其中一个与性能无关
        //但很重要的原因是，除了Serial收集器外，目前只有它能与CMS收集器配合工作。
        //ParNew收集器默认开启的收集线程数与CPU的数量相同。

        //3 Parallel Scavenge搜集器：Parallel Scavenge收集器是一个新生代收集器，使用复制算法，又是并行的多线程收集器。
        //最大的特点是： Parallel Scavenge收集器的目标是达到一个可控制的吞吐量。

        //4 Serial Old搜集器：Serial Old是Serial收集器的老年代版本，同样是一个单线程收集器，使用“标记-整
        //理”算法。这个收集器的主要意义也是在于给Client模式下虚拟机使用。
        //如果在Server模式下，它主要还有两大用途：
        //1.与Parallel Scavenge收集器搭配使用
        //2.作为CMS收集器的后备预案，在并发收集发生Conurrent Mode Failure使用。

        //5 Parallel Old搜集器：Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和“标记-整理”算法。
        //在注重吞吐量以及CPU资源敏感的场合，都可以优先考虑Parallel Scavenge+Parallel Old收集器

        //6 CMS（Concurrent Mark Sweep）搜集器：是HotSpot虚拟机中第一款真正意义上的并发收集器，它第一次实现了让垃圾收集
        //线程与用户线程同时工作。

        //7 G1搜集器：是一款面向服务端应用的垃圾收集器。使用G1收集器时，Java堆内存布局与其他收集器有很大差别，它将整个Java堆划
        //分成为多个大小相等的独立区域。 G1跟踪各个Region里面的垃圾堆积的价值大小
        //（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列
        //表，每次根据允许的收集时间，优先回收价值最大的Region。
}