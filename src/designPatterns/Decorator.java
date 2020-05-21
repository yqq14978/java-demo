package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/15
 * @Time:10:48
 */
public class Decorator {

    public static void main(String[] args) {
        HeroDecorator superManDecorator = new HeroArmsDecorator(new SuperMan());
        superManDecorator.fight();
        HeroDecorator ironManDecorator = new HeroArmsDecorator(new IronMan());
        ironManDecorator.fight();
    }

}

interface Hero{
    void fight();
}

class SuperMan implements Hero{
    @Override
    public void fight() {
        System.out.println("超人在战斗");
    }
}

class IronMan implements Hero{
    @Override
    public void fight() {
        System.out.println("钢铁侠在战斗");
    }
}

abstract class HeroDecorator implements Hero{

    protected Hero hero;

    public HeroDecorator(Hero hero){
        this.hero = hero;
    }

}

class HeroArmsDecorator extends HeroDecorator{
    public HeroArmsDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public void fight() {
        System.out.print("拿着武器的");
        hero.fight();
    }
}
