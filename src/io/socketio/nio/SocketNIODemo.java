package io.socketio.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/7/21
 * @Time:11:45
 */
public class SocketNIODemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9091));
        socketChannel.configureBlocking(false);

        //如果设置成非阻塞，直接通过BIO去回去连接会报异常
//        ServerSocket serverSocket = socketChannel.socket();
//        Socket socket = serverSocket.accept();
//        System.out.println("连接成功。。。对方端口：" + socket.getPort());

        //因为是非阻塞的，所以刚刚启动时不会有连接接入，会直接执行下面的代码
        SocketChannel client = socketChannel.accept();
        if (client != null){
            Socket socket = client.socket();
            System.out.println("连接成功——对方端口：" + socket.getPort());
        }else {
            System.out.println("未连接......");
        }

        //通过自旋的方式一直去访问服务端，检测是否有新的客户端接入
        while (true){
            Thread.sleep(1000);
            SocketChannel newClient = socketChannel.accept();
            if(newClient != null){
                Socket socket = newClient.socket();
                System.out.println("连接成功——对方端口：" + socket.getPort());

                //这一步读取数据还是会阻塞
//                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                System.out.println("接收消息：" + reader.readLine());

                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                if (newClient.read(byteBuffer) > 0){
                    byteBuffer.flip();
                    byte[] info = new byte[byteBuffer.limit()];
                    byteBuffer.get(info);
                    System.out.println("新消息：" + new String(info));
                    byteBuffer.clear();
                }
            }else {
                System.out.println("loading......");
            }
        }
    }

}
