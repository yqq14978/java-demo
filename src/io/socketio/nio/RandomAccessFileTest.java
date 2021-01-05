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
        RandomAccessFile randomAccessFile =
                new RandomAccessFile("D:\\others\\configserver\\config\\application.properties" , "rw");

        System.out.println("文件大小：" + randomAccessFile.length());
        System.out.println(randomAccessFile.read());
    }
}
