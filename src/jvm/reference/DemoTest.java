package jvm.reference;

import sun.nio.ch.DirectBuffer;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.WeakHashMap;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/12/21
 * @Time:15:40
 */
public class DemoTest {

    private static final Basic B = new Basic();

    public static void main(String[] args) throws InterruptedException {
        Basic basic = new Basic();
        //用四倍空间
//        int[] sum2 = new int[1024*1024*100];
        //用八倍空间
//        long[] sum3 = new long[1024*1024*100];
//        ByteBuffer.allocate(1024*1024*100);
        //软引用
        SoftReference<Basic> softReference = new SoftReference(basic);
        for (;;){
            if(softReference.get() == null){
                System.out.println("虚引用对象被回收");
                break;
            }
            ByteBuffer.allocate(50*1024*1024);
            Thread.sleep(20);
        }
        //弱引用
//        WeakReference<Basic> weakReference = new WeakReference<>(new Basic());
//        basic = null;
//        System.gc();
//        Thread.sleep(1000);
//        for (;;){
//            if(weakReference.get() == null){
//                System.out.println("虚引用对象被回收");
//                System.out.println(weakReference.enqueue());
//                break;
//            }
//            new Object();
//            System.out.println(weakReference.enqueue());
//        }
        //虚引用
//        PhantomReference<Basic> phantomReference = new PhantomReference(B , new ReferenceQueue());
//        System.out.println(phantomReference.get());
        System.exit(0);
    }
}
