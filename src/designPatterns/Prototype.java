package designPatterns;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/11
 * @Time:11:02
 */
public class Prototype {

    public static void main(String[] args) {
        String name = "dolly";
        Dolly dolly1 = new Dolly(name);
        DollyCache.cache(name , dolly1);
        System.out.println(dolly1.name);
        Dolly dolly2 = DollyCache.cloneDolly(name);
        dolly2.name = "dolly2";
        System.out.println(dolly1.name);
        Dolly dolly3 = DollyCache.getDolly(name);
        dolly3.name = "dolly3";
        System.out.println(dolly1.name);
    }

}

class Dolly implements Cloneable {

    public String name;

    public Dolly (String name){
        this.name = name;
    }

    @Override
    public Object clone(){
        Object dollyClone = null;
        try {
            dollyClone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return dollyClone;
    }

}

class DollyCache {
    private static final ConcurrentHashMap<String , Dolly> DOLLIES = new ConcurrentHashMap<>();

    public static Dolly getDolly(String name){
        return DOLLIES.get(name);
    }

    public static Dolly cloneDolly(String name){
        return (Dolly) DOLLIES.get(name).clone();
    }

    public static Dolly cache(String name , Dolly dolly){
        return DOLLIES.put(name , dolly);
    }

}
