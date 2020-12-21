package jdk8;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/12/18
 * @Time:10:19
 */
public interface Basic {

    default void top(){
        System.out.println("顶级接口：" + Basic.class);
    }

}
