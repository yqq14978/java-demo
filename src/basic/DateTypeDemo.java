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
//        dataCompare();
        conversion();
//        stringTest();
    }

    public static void conversion() {
        int a = 785411;
        float b = a;
        System.out.println(b);
        int c = Short.MAX_VALUE;
        System.out.println(c);
        short d = (short) 4555;
        System.out.println(d);
        float e = 0.1234567F;
        System.out.println(e);
        //小浮点转大浮点丢失进度
        double f = e;
        System.out.println(f);
        e = 9.0F;
        f = 1.0;
        String str = "3.0";
        System.out.println(e + f + str);
    }

    public static void dataCompare() {
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

    private static void stringTest(){
        String a = new String("11");
        String b = new String("1") + new String("1");
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

}
