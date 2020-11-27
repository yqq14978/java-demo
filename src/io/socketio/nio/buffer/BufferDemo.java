package io.socketio.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

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
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
        directBufferTest();
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

//        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
    }

    private static void test4(){
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (byte i = 0; i < buffer.capacity(); i++){
            buffer.put(i);
        }

        buffer.position(2);
        buffer.limit(7);
        ByteBuffer sliceBuffer = buffer.slice();

        for (byte i = 0; i < sliceBuffer.capacity(); i++){
//            i = (byte) (i * 2);
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i , b);
        }

        buffer.clear();
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }

    private static void test5(){
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (byte i = 0; i < buffer.capacity(); i++){
            buffer.put(i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.get(0));
        readOnlyBuffer.put(0 , (byte)2);
    }

    private static void test6() throws IOException {
        ByteBuffer heapByteBuffer = ByteBuffer.allocate(1024);
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(1024);

        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        long start = System.currentTimeMillis();
        System.out.println("start-" + start);
        inputChannel.read(heapByteBuffer);
        heapByteBuffer.flip();
        outputChannel.write(heapByteBuffer);
        System.out.println("heapByteBuffer完成时间：" + (System.currentTimeMillis() - start));

        long startD = System.currentTimeMillis();
        System.out.println("startD-" + startD);
        inputChannel.read(directByteBuffer);
        directByteBuffer.flip();
        outputChannel.write(directByteBuffer);
        System.out.println("directByteBuffer完成时间：" + (System.currentTimeMillis() - startD));

        System.exit(0);
    }

    private static void test7() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress socketAddress = new InetSocketAddress(8899);
        serverSocketChannel.bind(socketAddress);

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);
        int messageLength = 2 + 3 + 4;

        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("客户端接入。。。");

        int read = 0;
        while (read < messageLength){
            read += socketChannel.read(buffers);
            System.out.println("read-" + read);
            Arrays.stream(buffers).map(byteBuffer -> "position：" + byteBuffer.position()).forEach(System.out::println);
        }
        Arrays.stream(buffers).forEach(byteBuffer -> {
            byteBuffer.flip();
        });
        socketChannel.write(buffers);
    }

    public static void directBufferTest() throws IOException {
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(1024*1024*1024);
        System.in.read();
    }

}
