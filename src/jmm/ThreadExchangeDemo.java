package jmm;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IDEA
 *
 * 线程间的数据交换
 *
 *  @author:yeqq
 * @Date:2020/8/5
 * @Time:10:42
 */
public class ThreadExchangeDemo {

    public static void main(String[] args) throws InterruptedException, IOException {
        //synchronized
//        System.out.println("==============synchronized================");
//        synCommit();

        //wait/notify机制
//        System.out.println("==============wait/notify================");
//        w_ncommit();

        //while轮询
//        System.out.println("==============while================");
//        whileCommit();

        //管道
        System.out.println("==============管道================");
        pipeCommit();
//        LockSupport.park();
//        CountDownLatch countDownLatch = new CountDownLatch(4);
//        countDownLatch.countDown();
    }

    public static void synCommit(){
        MyLock lock = new MyLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (lock.a < 100){
                        lock.sout("线程1");
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (lock.a < 100){
                        lock.sout("线程2");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }

    public static void w_ncommit(){
        MyLock lock = new MyLock();
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程B开始运行");
            }
        });
        threadB.start();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程A开始运行");
                synchronized(lock){
                    lock.notify();
                }
            }
        });
        threadA.start();
    }

    public static void whileCommit(){
        MyLock lock = new MyLock();
        Thread treadX = new Thread(new Runnable() {
            @Override
            public void run() {
                while (lock.temp < 3){
                    lock.temp++;
                    System.out.println("线程X变量temp" + lock.temp);
                }
            }
        });
        Thread treadY = new Thread(new Runnable() {
            @Override
            public void run() {
                while (lock.temp >= 3 && lock.temp < 6){
                    lock.temp++;
                    System.out.println("线程Y修改变量temp" + lock.temp);
                }
            }
        });
        treadX.start();
        treadY.start();
    }

    public static void pipeCommit() throws IOException {
        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream();
        inputStream.connect(outputStream);
        Thread threadIn = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                byte[] data = new byte[1024];
                while (true){
                    try {
                        inputStream.read(data);
                        Thread.sleep(500L);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("接收方线程获取消息：" + new String(data));
                }
            }
        });
        Thread threadOut = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] data = new String("hello").getBytes();
                while (true){
                    try {
                        outputStream.write(data);
                        Thread.sleep(500L);
                    } catch (IOException e) {
                        e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("输出方线程发送消息：" + new String(data));
                }
            }
        });
        threadIn.start();
        threadOut.start();
    }

}

class MyLock{

    public int a = 0;
    public volatile int temp = 0;

    public synchronized void sout(String threadName){
        System.out.println(threadName + "开始运行");
        a++;
        System.out.println("变量a——" + a);
    }
}
