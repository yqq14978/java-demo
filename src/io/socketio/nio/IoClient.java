package io.socketio.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/25
 * @Time:11:39
 */
public class IoClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1" , 9090));
        socketChannel.configureBlocking(true);

        String fileName = "D:\\othersTools\\安装包\\redis-4.0.14.tar.gz";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
//        FileChannel fileChannel = new FileInputStream("input.txt").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(4096);
        int readCount;
        int total = 0;
        long startTime = System.currentTimeMillis();
        while ((readCount = fileChannel.read(buffer)) != -1){
            total += readCount;
            buffer.flip();
            socketChannel.write(buffer);
            buffer.rewind();
        }

        System.out.println("数据大小：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));

    }

}
