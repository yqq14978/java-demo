package io.socketio.nio.basic;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/12
 * @Time:15:40
 */
public class SocketNIOWorkerThread implements Runnable {

    private SocketChannel socketChannel;

    public SocketNIOWorkerThread(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }

    @Override
    public void run(){
        //这一步读取数据还是会阻塞
//                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                System.out.println("接收消息：" + reader.readLine());
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        try {
            while (true){
                if (socketChannel.read(byteBuffer) > 0){
                    byteBuffer.flip();
                    byte[] info = new byte[byteBuffer.limit()];
                    byteBuffer.get(info);
                    System.out.println("新消息：" + new String(info));
                    byteBuffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
