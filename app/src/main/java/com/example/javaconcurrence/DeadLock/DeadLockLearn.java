package com.example.javaconcurrence.DeadLock;

import android.accounts.Account;

public class DeadLockLearn {
    //死锁的产生条件
        //1. 互斥条件：一个资源每次只能被一个线程使用。
        //2. 请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放。
        //3. 不剥夺条件：线程已获得的资源，在未使用完之前，不能强行剥夺。
        //4. 循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。

    //死锁类型：
        //1 静态的锁顺序死锁：a和b两个方法都需要获得A锁和B锁。一个线程执行a方法且已经获得了A锁，在等
        //待B锁；另一个线程执行了b方法且已经获得了B锁，在等待A锁。这种状态，就是
        //发生了静态的锁顺序死锁。

        //可能发生静态锁顺序死锁的代码
        class StaticLockOrderDeadLock1{
            private final Object lockA = new Object();
            private final Object lockB = new Object();

            public void a(){
                synchronized (lockA){
                    synchronized (lockB){
                        System.out.println("function a");
                    }
                }
            }

            public void b(){
                synchronized (lockB){
                    synchronized (lockA){
                        System.out.println("function b");
                    }
                }
            }
        }

        //解决方法：所有需要多个锁的线程，都要以相同的顺序来获得锁
        class StaticLockOrderDeadLock2 {
            private final Object lockA = new Object();
            private final Object lockB = new Object();
            public void a() {
                synchronized (lockA) {
                    synchronized (lockB) {
                        System.out.println("function a");
                    }
                }
            }
            public void b() {
                synchronized (lockA) {
                    synchronized (lockB) {
                        System.out.println("function b");
                    }
                }
            }
        }

        //2 动态的锁顺序死锁：动态的锁顺序死锁是指两个线程调用同一个方法时，传入的参数颠倒造成的死锁。
        //如下代码，一个线程调用了transferMoney方法并传入参数accountA,accountB；另
        //一个线程调用了transferMoney方法并传入参数accountB,accountA。此时就可能发
        //生在静态的锁顺序死锁中存在的问题，即：第一个线程获得了accountA锁并等待
        //accountB锁，第二个线程获得了accountB锁并等待accountA锁。
//可能发生动态锁顺序死锁的代码
        /*class DynamicLockOrderDeadLock {
            public void transefMoney(Account fromAccount, Account toAccount, Double amount) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        //...
                        fromAccount.minus(amount);
                        toAccount.add(amount);
                        //...
                    }
                }
            }
        }*/

    //正确的代码
    /*class DynamicLockOrderDeadLock {
        private final Object myLock = new Object();
        public void transefMoney(final Account fromAccount, final Ac
                count toAccount, final Double amount) {
            class Helper {
                public void transfer() {
                    //...
                    fromAccount.minus(amount);
                    toAccount.add(amount);
                    //...
                }
            }
            int fromHash = System.identityHashCode(fromAccount);
            int toHash = System.identityHashCode(toAccount);
            if (fromHash < toHash) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                        死锁
                        220
                    }
                }
            } else if (fromHash > toHash) {
                synchronized (toAccount) {
                    synchronized (fromAccount) {
                        new Helper().transfer();
                    }
                }
            } else {
                synchronized (myLock) {
                    synchronized (fromAccount) {
                        synchronized (toAccount) {
                            new Helper().transfer();
                        }
                    }
                }
            }
        }
    }*/

    //3 协作对象之间发生的死锁：一个线程调用了Taxi对象的setLocation方法，另一个线程调用了Dispatcher对
    //象的getImage方法。此时可能会发生，第一个线程持有Taxi对象锁并等待
    //Dispatcher对象锁，另一个线程持有Dispatcher对象锁并等待Taxi对象锁。
    //可能发生死锁
    /*class Taxi {
        private Point location, destination;
        private final Dispatcher dispatcher;
        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }
        public synchronized Point getLocation() {
            死锁
            221
            return location;
        }
        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination))
                dispatcher.notifyAvailable(this);//外部调用方法，可能等
            待Dispatcher对象锁
        }
    }
    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxis;
        public Dispatcher() {
            taxis = new HashSet<Taxi>();
            availableTaxis = new HashSet<Taxi>();
        }
        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }
        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi t : taxis)
                image.drawMarker(t.getLocation());//外部调用方法，可能
            等待Taxi对象锁
            return image;
        }
    }*/


    //解决方法：需要使用开放调用，即避免在持有锁的情况下调用外部的方法。
    //正确的代码
    /*class Taxi {
        private Point location, destination;
        private final Dispatcher dispatcher;
        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }
        public synchronized Point getLocation() {
            return location;
        }
        public void setLocation(Point location) {
            boolean flag = false;
            synchronized (this) {
                this.location = location;
                flag = location.equals(destination);
            }
            if (flag)
                dispatcher.notifyAvailable(this);//使用开放调用
        }
    }
    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxis;
        public Dispatcher() {
            taxis = new HashSet<Taxi>();
            availableTaxis = new HashSet<Taxi>();
        }
        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }
        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                死锁
                223
                copy = new HashSet<Taxi>(taxis);
            }
            Image image = new Image();
            for (Taxi t : copy)
                image.drawMarker(t.getLocation());//使用开放调用
            return image;
        }
    }*/


    //总结：在写代码时，要确保线程在获取多个锁时采用一致的顺序。同时，要避免在持有锁的情况下调用外部方法。
}
