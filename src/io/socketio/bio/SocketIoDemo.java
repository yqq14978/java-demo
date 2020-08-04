package io.socketio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/7/21
 * @Time:10:04
 */
public class SocketIoDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        //阻塞，等待连接
        Socket socket = serverSocket.accept();
        System.out.println("连接成功——客户端端口：" + socket.getPort());
        InputStream clientStream = socket.getInputStream();
        System.out.println("监控数据......");
        //读取数据的时候阻塞，直到有数据输入数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientStream));
        System.out.println("通讯消息：" + reader.readLine());
    }

}
