package basic;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/8/6
 * @Time:11:17
 */
public class DateTypeDemo {

    public static void main(String[] args) {
        int a1 = 200;
        int a2 = new Integer(200);
        Integer a3 = 200;
        Integer a4 = 200;
        Integer a5 = a4;
        Integer b1 = 100;
        Integer b2 = 100;
        System.out.println("a1 == a2：" + (a1 == a2));
        System.out.println("a2 == a3：" + (a2 == a3));
        System.out.println("a4 == a3：" + (a4.equals(a3)));
        System.out.println("a4 == a5：" + (a4 == a5));
        System.out.println("a4 == a3：" + (a4 == a3));
        System.out.println("b1 == b2：" + (b1 == b2));
    }

}
