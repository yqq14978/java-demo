package jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/12/21
 * @Time:11:33
 */
public class Basic {

    public long time = System.currentTimeMillis();

    public void sayHello(){
        System.out.println("hello world");
    }
}
