package io.socketio;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/25
 * @Time:15:26
 */
public class ZeroCopyClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1" , 9090));
        socketChannel.configureBlocking(true);

        String fileName = "D:\\othersTools\\安装包\\redis-4.0.14.tar.gz";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();
        long total = fileChannel.transferTo(0 , fileChannel.size() , socketChannel);
        System.out.println("数据大小：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));
        System.exit(0);
    }
}
