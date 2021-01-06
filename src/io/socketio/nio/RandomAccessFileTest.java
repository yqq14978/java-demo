package io.socketio.nio;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2021/1/5
 * @Time:15:14
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile readRandom =
                new RandomAccessFile("D:\\others\\configserver\\config\\application.properties" , "rw");
        RandomAccessFile writeRandom =
                new RandomAccessFile("D:\\others\\configserver\\config\\application0.properties" , "rw");

        System.out.println("文件大小：" + readRandom.length());
//        System.out.println(readRandom.read());
        byte[] data = new byte[1024];
        System.out.println(readRandom.read(data));
        writeRandom.write(data);
//        randomAccessFile.seek();
//        randomAccessFile.getFilePointer();
    }
}
