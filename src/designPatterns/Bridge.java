package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/12
 * @Time:16:49
 *
 * 个人理解：桥接模式的原理就是将有多个抽象属性的实体，将其中的抽象属性进行分离，是的多个属性之间进行解耦，可以自由组合
 * 比如：笔有不同的种类，钢笔、圆珠笔、等，然后每种笔都有不同的墨水颜色：黑色、蓝色、红色，等。如果把这些属性都写在笔这
 * 一个接口中，那么这个接口势必会非常的庞大，而且子类也会实现很多不相干的方法。
 */
public class Bridge {

    public static void main(String[] args) {
        Pen pen1 = new BallpointPen(new RedInk());
        pen1.write();
        Pen pen2 = new FountainPen(new BlackInk());
        pen2.write();
    }

}

interface Pen{
    void write();
}

class BallpointPen implements Pen{

    private Ink ink;

    public BallpointPen(Ink ink){
        this.ink = ink;
    }

    @Override
    public void write() {
        System.out.println("用圆珠笔写出了" + ink.getInk() + "的字");
    }
}

class FountainPen implements Pen{

    private Ink ink;
    public FountainPen(Ink ink){
        this.ink = ink;
    }

    @Override
    public void write() {
        System.out.println("用钢笔写出了" + ink.getInk() + "的字");
    }
}

interface Ink{
    String getInk();
}

class BlackInk implements Ink{
    @Override
    public String getInk() {
        return "黑色";
    }
}

class RedInk implements Ink{
    @Override
    public String getInk() {
        return "红色";
    }
}
