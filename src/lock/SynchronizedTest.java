package lock;

import java.util.concurrent.CountDownLatch;

public class SynchronizedTest {

    private int x = 0;
    private static CountDownLatch countDownLatch;

    public SynchronizedTest(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 1000;
        //不加锁修改共享变量
        SynchronizedTest test1 = new SynchronizedTest(new CountDownLatch(n));
        doTest(n , test1);
        //加锁修改共享变量
        countDownLatch.await();
        SynchronizedTest test2 = new SynchronizedTest(new CountDownLatch(n));
        doTest(n , test2);
    }

    public void add(){
        x++;
        countDownLatch.countDown();
    }

    public void synchAdd(){
        synchronized (this){
            x++;
            countDownLatch.countDown();
        }
    }

    public static void doTest(int n , SynchronizedTest test) throws InterruptedException {
        for (int i = 0; i <= n; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.add();
                }
            });
            thread.start();
            Thread.sleep(100);
            countDownLatch.countDown();
        }
    }

}
