package io.socketio.bio.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/12
 * @Time:15:51
 */
public class SocketWorkerThread implements Runnable {

    private Socket socket;

    public SocketWorkerThread (Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("连接成功——客户端端口：" + socket.getPort());
            InputStream clientStream = null;
            clientStream = socket.getInputStream();
            System.out.println("监控数据......");

            //读取数据的时候阻塞，直到有数据输入数据
            while (true){
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientStream));
                System.out.println("通讯消息：" + reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
