package io.socketio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/23
 * @Time:9:22
 */
public class SocketMuIOServerDemo {

    private static Map<String , SocketChannel> clientMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector , SelectionKey.OP_ACCEPT);

        while (true){
            if(selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

                while (selectionKeyIterator.hasNext()){
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    if(selectionKey.isAcceptable()){
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel client = channel.accept();
                        client.configureBlocking(false);
                        client.register(selector , SelectionKey.OP_READ);

                        Iterator<String> iterator =  clientMap.keySet().iterator();
                        String info = "新连接加入：" + client.getRemoteAddress();
                        while (iterator.hasNext()){
                            SocketChannel val = clientMap.get(iterator.next());
                            ByteBuffer buffer = ByteBuffer.allocate(info.getBytes().length);
                            buffer.put(info.getBytes());
                            val.write(buffer);
                        }

                        String address = client.getRemoteAddress().toString();
                        System.out.println(info);
                        clientMap.put(address , client);
                    }else if (selectionKey.isReadable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        client.configureBlocking(false);
                        client.read(buffer);

                        String address = client.getRemoteAddress().toString();
                        buffer.flip();
                        byte[] b = new byte[buffer.limit()];
                        buffer.get(b);
                        String info = "新消息（" + address + "）：" + new String(b);

                        Iterator<String> iterator = clientMap.keySet().iterator();
                        while (iterator.hasNext()){
                            String key = iterator.next();
                            if(!key.equals(address) && clientMap.get(key) != null){

                                SocketChannel val = clientMap.get(key);
                                buffer.clear();
                                buffer.put(info.getBytes());
                                val.write(buffer);
                            }
                        }

                        System.out.println(info);
                    }

                    selectionKeyIterator.remove();
                }
            }
        }
    }
}
