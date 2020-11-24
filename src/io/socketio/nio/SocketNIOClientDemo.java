package io.socketio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/23
 * @Time:11:46
 */
public class SocketNIOClientDemo {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1" , 9090));

        if(socketChannel.isConnectionPending()){
            socketChannel.finishConnect();
            while (true){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                if(socketChannel.read(buffer) > 0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    System.out.println(new String(bytes));
                }
            }
        }
    }
}
