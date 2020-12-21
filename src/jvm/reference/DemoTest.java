package jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/12/21
 * @Time:15:40
 */
public class DemoTest {

    public static void main(String[] args) {
        Basic basic = new Basic();
        //软引用
        SoftReference<Basic> softReference = new SoftReference(basic);
        System.out.println(softReference.get());
        //弱引用
        WeakReference<Basic> weakReference = new WeakReference<>(basic);
        System.out.println(weakReference.get());
        //虚引用
        PhantomReference<Basic> phantomReference = new PhantomReference(basic , new ReferenceQueue());
        System.out.println(phantomReference.get());
    }
}
