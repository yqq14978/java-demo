package collectionDemo.List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/8/24
 * @Time:9:28
 */
public class ListTest {

    public static void main(String[] args) {
        vectorTest();
    }

    private static void vectorTest(){
        String[] strs = new String[15];
        Vector vector0 = new Vector();
        Vector vector1 = new Vector(20);
        Vector vector2 = new Vector(20 , 10);
        Vector vector3 = new Vector(vector2);
    }

    private static void arrayListTest(){
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList(50);
        ArrayList arrayList2 = new ArrayList(arrayList1);
    }

    private static void linkendListTest(){
        LinkedList linkedList0 = new LinkedList();
        LinkedList linkedList1 = new LinkedList(linkedList0);
    }

    private static void copyOnWriteListTest(){
        CopyOnWriteArrayList copyOnWriteArrayList0 = new CopyOnWriteArrayList();
        CopyOnWriteArrayList copyOnWriteArrayList1 = new CopyOnWriteArrayList(copyOnWriteArrayList0);
        String[] strs = new String[10];
        CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList(strs);
    }

}
