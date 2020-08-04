package io.socketio.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/7/29
 * @Time:10:05
 */
public class SocketMuIODemo {

    private static Selector selector = null;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(9092));

        selector = Selector.open();
        channel.register(selector , SelectionKey.OP_ACCEPT);

        while (true){
            while (selector.select(0) > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if(key.isAcceptable()){
                        acceptHandler(key);
                    }else if(key.isReadable()){
                        readHandler(key);
                    }
                }
            }
        }
    }

    private static void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel client = channel.accept();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.register(selector , SelectionKey.OP_READ , buffer);
    }

    private static void readHandler(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();

        buffer.clear();
        int read = 0;
        while (true){
            read = client.read(buffer);
            if(read > 0){
                buffer.flip();
                while (buffer.hasRemaining()){
                    byte[] info = new byte[buffer.limit()];
                    buffer.get(info);
                    System.out.println("新消息：" + new String(info));
                    buffer.clear();        break;
                }
            }else if(read == 0){
                break;
            }else {
                client.close();
                break;
            }
        }
    }

}

