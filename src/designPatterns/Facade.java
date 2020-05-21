package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/15
 * @Time:14:06
 */
public class Facade {

    public static void main(String[] args) {
        JarvisAttackControl jarvisAttackControl = new JarvisAttackControl();
        jarvisAttackControl.mark1Attack();
        jarvisAttackControl.mark2Attack();
        jarvisAttackControl.mark3Attack();
    }

}

interface Jarvis {
    void attack();
}

class Mark1 implements Jarvis{
    @Override
    public void attack() {
        System.out.println("白色激光");
    }
}

class Mark2 implements Jarvis{
    @Override
    public void attack() {
        System.out.println("红色激光");
    }
}

class Mark3 implements Jarvis{
    @Override
    public void attack() {
        System.out.println("黄色激光");
    }
}

class JarvisAttackControl{

    private Mark1 mark1;
    private Mark2 mark2;
    private Mark3 mark3;
    public JarvisAttackControl (){
        mark1 = new Mark1();
        mark2 = new Mark2();
        mark3 = new Mark3();
    }

    public void mark1Attack(){
        mark1.attack();
    }

    public void mark2Attack(){
        mark2.attack();
    }

    public void mark3Attack(){
        mark3.attack();
    }

}
