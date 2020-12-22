package jvm.reference;

import sun.nio.ch.DirectBuffer;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/12/21
 * @Time:15:40
 */
public class DemoTest {

    public static void main(String[] args) throws InterruptedException {
        Basic basic = new Basic();
        //用四倍空间
//        int[] sum2 = new int[1024*1024*100];
        //用八倍空间
//        long[] sum3 = new long[1024*1024*100];
//        ByteBuffer.allocate(1024*1024*100);
        //软引用
//        SoftReference<Basic> softReference = new SoftReference(basic);
//        System.out.println(softReference.get());
        //弱引用
        WeakReference<Basic> weakReference = new WeakReference<>(basic);
//        basic = null;
        System.gc();
        Thread.sleep(1000);
//        weakReference.get();
        //虚引用
//        PhantomReference<Basic> phantomReference = new PhantomReference(basic , new ReferenceQueue());
//        System.out.println(phantomReference.get());
        System.exit(0);
    }
}
