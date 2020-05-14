package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/8
 * @Time:11:12
 */
public class FactoryMethod {
    public static void main(String[] args) {
        ColaFactory pepsiFactory = new PepsiFactory();
        pepsiFactory.getCola().getTaste();
        ColaFactory cocoFactory = new CocoFactory();
        cocoFactory.getCola().getTaste();
    }
}

interface Cola{
    void getTaste();
}

class Pepsi implements Cola{

    @Override
    public void getTaste() {
        System.out.println("百事可乐味道更温和");
    }
}

class Coco implements Cola{
    @Override
    public void getTaste() {
        System.out.println("可口可乐味道更刺激");
    }
}

interface ColaFactory{
    Cola getCola();
}

class PepsiFactory implements ColaFactory{

    @Override
    public Cola getCola() {
        return new Pepsi();
    }
}

class CocoFactory implements ColaFactory{
    @Override
    public Cola getCola() {
        return new Coco();
    }
}
