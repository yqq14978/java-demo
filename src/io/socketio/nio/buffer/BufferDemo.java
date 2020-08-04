package io.socketio.nio.buffer;

import java.nio.ByteBuffer;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/8/4
 * @Time:15:25
 */
public class BufferDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        String str1 = "a";
        String str2 = "b";
        byteBuffer1.put(str1.getBytes());
        byteBuffer2.put(str1.getBytes());
        System.out.println(byteBuffer1.equals(byteBuffer2));
        byteBuffer2.clear();
        byteBuffer1.put(str2.getBytes());
        System.out.println(byteBuffer1.compareTo(byteBuffer2));
    }

}
