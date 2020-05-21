package lock;

import java.util.concurrent.CountDownLatch;

public class SynchronizedTest {

    private int x = 1;
    private volatile int v = 1;
    private static int N = 10000;
    private static CountDownLatch countDownLatch = new CountDownLatch(N);

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test1 = new SynchronizedTest();
        SynchronizedTest test2 = new SynchronizedTest();
        doTest(N , test1 , test2);
    }

    public void add(){
        System.out.println("未加锁======" + x++);
    }

    public void addV(){
        System.out.println("volatile======" + v++);
    }

    public void synchAdd(){
        synchronized (this){
            System.out.println("加锁======" + x++);
        }
    }

    public static void doTest(int n , SynchronizedTest test1 , SynchronizedTest test2) throws InterruptedException {
        for (int i = 0; i < n; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    test1.add();
//                    test1.addV();
                    test2.synchAdd();
                }
            });
            thread.start();
            countDownLatch.countDown();
        }
    }

}
