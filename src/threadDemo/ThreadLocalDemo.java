package threadDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLocalDemo {

    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
//        Executors.newSingleThreadExecutor();
//        Executors.newCachedThreadPool();
//        Executors.newFixedThreadPool();
//        Executors.newScheduledThreadPool();
//        Executors.newSingleThreadScheduledExecutor();
//        Executors.newWorkStealingPool();
//
//        ThreadLocal threadLocal = new ThreadLocal();
//        threadLocal.set();
//        threadLocal.get();
//
//        ReentrantLock reentrantLock = new ReentrantLock();



        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(1212);
        Thread t1 = new Thread(()->{
            System.out.println(threadLocal.get());
            threadLocal.set("t1");
            System.out.println(threadLocal.get());
        });
        t1.start();
        Thread t2 = new Thread(()->{
            System.out.println(threadLocal.get());
            threadLocal.set("t2");
            System.out.println(threadLocal.get());
        });
        t2.start();
    }

}
