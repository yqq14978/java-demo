package io.socketio.nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/8/4
 * @Time:15:25
 */
public class BufferDemo {

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        test3();
    }

    private static void test1(){
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        String str1 = "a";
        String str2 = "b";
        byteBuffer1.put(str1.getBytes());
        byteBuffer2.put(str1.getBytes());
//        System.out.println(byteBuffer1.equals(byteBuffer2));
        byteBuffer2.clear();
        byteBuffer1.put(str2.getBytes());
//        System.out.println(byteBuffer1.compareTo(byteBuffer2));

        String str3 = "123456";
        byteBuffer2.clear();
        byteBuffer2.put(str3.getBytes());
        System.out.println(byteBuffer2.position());
        System.out.println(byteBuffer2.limit());
        System.out.println(byteBuffer2.capacity());
        byteBuffer2.flip();
        System.out.println(byteBuffer2.position());
        System.out.println(byteBuffer2.limit());
        System.out.println(byteBuffer2.capacity());
    }

    private static void test2() throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        while (true){
//            buffer.clear();

            /*不注释掉clear方法，会将buffer的容量复原，也就是pos=0，limit=512，在进行IO读取时发现还有容量可用会继续读取，
            * 但是因为channel没有重置会发现文件已经读取完毕，所以读取不到剩余内容，返回的是-1.
            *
            * 注释掉clear方法，buffer不会复原（不管是否调用clear方法buffer中的数据都不会清空），pos=limit=12，这个时候进
            * 行IO读取的时候会判定为容量已耗尽（pos-limit=0），所以不会进行读取操作，直接返回0（IOUtil.readIntoNativeBuffer）
            */
            int read = inputChannel.read(buffer);
            System.out.println("read = " + read);
            if(read == -1){
                break;
            }

            buffer.flip();
            outputChannel.write(buffer);
        }
    }

    private static void test3(){
        ByteBuffer buffer = ByteBuffer.allocate(512);

        buffer.putChar('a');
        buffer.putDouble(12121.121);
        buffer.putInt(48848);
        buffer.putLong(454654L);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
    }

}
