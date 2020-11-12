package io.socketio.bio;

import io.socketio.bio.basic.SocketWorkerThread;

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
        while (true){
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(new SocketWorkerThread(socket));
            thread.start();
            System.out.println("laoding...");
        }
    }

}
