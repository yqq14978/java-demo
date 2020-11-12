package io.socketio.nio;

import io.socketio.nio.basic.SocketNIOWorkerThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
//        SocketChannel client = socketChannel.accept();
//        if (client != null){
//            Socket socket = client.socket();
//            System.out.println("连接成功——对方端口：" + socket.getPort());
//        }else {
//            System.out.println("未连接......");
//        }

        //通过自旋的方式一直去访问服务端，检测是否有新的客户端接入
        while (true){
            Thread.sleep(1000);
            SocketChannel newClient = socketChannel.accept();
            if(newClient != null){
                Socket socket = newClient.socket();
                System.out.println("连接成功——对方端口：" + socket.getPort());

                Thread thread = new Thread(new SocketNIOWorkerThread(newClient));
                thread.start();
            }else {
                System.out.println("laoding...");
            }
        }
    }

}
