package queue;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/4/22
 * @Time:13:55
 */
public class QueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        //非阻塞，通过cas的方式去添加节点
        concurrentLinkedQueue.add(1);
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        //阻塞,底层使用到了reentra
        arrayBlockingQueue.add(1);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        DelayQueue delayQueue = new DelayQueue();
        PriorityQueue priorityQueue = new PriorityQueue();
    }

}
