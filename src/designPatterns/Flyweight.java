package designPatterns;

import java.util.HashMap;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/19
 * @Time:14:31
 */
public class Flyweight {

    public static void main(String[] args) {
        MonkeyHair monkeyHair = new MonkeyHair();
        monkeyHair.monkeyKingReply("孙行者");
        monkeyHair.monkeyKingReply("美猴王");
    }

}

interface MonkeyKing{
    void reply();
}

class SunXingZhe implements MonkeyKing{
    @Override
    public void reply() {
        System.out.println("我是孙行者");
    }
}

class ZheXingSun implements MonkeyKing{
    @Override
    public void reply() {
        System.out.println("我是者行孙");
    }
}

class XingZheSun implements MonkeyKing{
    @Override
    public void reply() {
        System.out.println("我是行者孙");
    }
}

class MonkeyHair{

    private HashMap<String , MonkeyKing> monkeyKings;
    public MonkeyHair(){
        monkeyKings = new HashMap<>();
        monkeyKings.put("孙行者" , new SunXingZhe());
        monkeyKings.put("者行孙" , new ZheXingSun());
        monkeyKings.put("行者孙" , new XingZheSun());
    }

    public void monkeyKingReply(String name){
        MonkeyKing monkeyKing = monkeyKings.get(name);
        if(null == monkeyKing){
            System.out.println("吃俺老孙一棒");
            return;
        }
        monkeyKing.reply();
    }

}
