package basic;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/8/6
 * @Time:11:36
 */
public class EqualsDemo {

    public static void main(String[] args) {
        Demo demo1 = new Demo();
        Demo demo2 = new Demo();
        Demo demo3 = demo1;
        System.out.println(demo1.equals(demo2));
        System.out.println(demo1.equals(demo3));
        System.out.println(demo1 == demo2);
        System.out.println(demo1 ==demo3);
    }

}

class Demo {
    private int a = 1;
}
