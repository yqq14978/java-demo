package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/8
 * @Time:15:00
 */
public class Singleton {

    public static void main(String[] args) {
        System.out.println("懒汉模式检验——" + LazySingleton.getSingleton().equals(LazySingleton.getSingleton()));
        System.out.println("饿汉模式检验——" + HungrySingleton.getSingleton().equals(HungrySingleton.getSingleton()));
        System.out.println("双重检查锁（DCL）模式检验——" + DCLSingleton.getSingleton().equals(DCLSingleton.getSingleton()));
        System.out.println("内部类模式检验——" + InnerClassSingleton.getSingleton().equals(InnerClassSingleton.getSingleton()));
        System.out.println("枚举模式检验——" + EnumSingleton.getSingleton().equals(EnumSingleton.getSingleton()));
    }

}

/**
* @Author yeqq
* @Description 同步懒汉模式的缺点是会导致每次获取对象都是同步获取
* @Date 15:24 2020/5/8
* @Param
* @return
**/
class LazySingleton{
    private static LazySingleton singleton;
    private LazySingleton(){}

    public static synchronized LazySingleton getSingleton(){
        if(null == singleton){
            singleton = new LazySingleton();
        }
        return singleton;
    }
}

/**
* @Author yeqq
* @Description 饿汉模式的缺点是会导致只要加载类都会初始化，浪费空间
* @Date 15:25 2020/5/8
* @Param
* @return
**/
class HungrySingleton{
    private static final HungrySingleton SINGLETON = new HungrySingleton();
    private HungrySingleton(){}

    public static HungrySingleton getSingleton(){
        return SINGLETON;
    }
}

/**
* @Author yeqq
* @Description 双重检查锁需要加volatile的原因是防止（singleton = new DCLSingleton()）这一步发生重排序
 * singleton = new DCLSingleton()在底层操作可以分成三步：分配内存空间、初始化对象、设置singleton指向刚分配的内存地址
 * 这三步可能会发生重排序
 * 在多线程的情况下，可能出现先将singleton指向内存地址，然后再初始化对象的情况
 * 这样就会导致有可能获取到一个还未初始化的对象
* @Date 15:27 2020/5/8
* @Param
* @return
**/
class DCLSingleton{
    private static volatile DCLSingleton singleton;
    private DCLSingleton(){}

    public static DCLSingleton getSingleton(){
        if(null == singleton){
            synchronized (DCLSingleton.class){
                if(null == singleton) {
                    singleton = new DCLSingleton();
                }
            }
        }
        return singleton;
    }
}

/**
* @Author yeqq
* @Description 静态内部类实现单例的原理：
 * 虚拟机会保证一个类的<clinit>() 方法在多线程环境中被正确的加锁、同步，每次加载类的时候只会有一个线程进行初始化，所以是线程安全的。
* @Date 15:38 2020/5/8
* @Param
* @return
**/
class InnerClassSingleton{
    private static class InstanceClass{
        private static final InnerClassSingleton SINGLETON = new InnerClassSingleton();
    }
    private InnerClassSingleton(){}

    public static InnerClassSingleton getSingleton(){
        return InstanceClass.SINGLETON;
    }
}

/**
* @Author yeqq
* @Description 枚举实现单例的原理：
 * 枚举类反编译之后，每一个枚举元素其实都是它本身的一个静态常量实例，所以singleton只会初始化一次
 * 枚举在序列化的时候只是将每个枚举元素的名称输出，如（SINGLETON），反序列化的时候是通过名字（SINGLETON）去获取枚举对象
 * 所以序列化之后仍然是得到的同一个对象
* @Date 15:55 2020/5/8
* @Param
* @return
**/
class EnumSingleton{
    public static EnumSingleton getSingleton(){
        return EnumInstance.SINGLETON.singleton;
    }
    private EnumSingleton(){}

    enum EnumInstance{

        SINGLETON(new EnumSingleton());

        private EnumSingleton singleton;

        EnumInstance(EnumSingleton singleton) {
            this.singleton = singleton;
        }

        public EnumSingleton getSingleton(){
            return singleton;
        }
    }
}
