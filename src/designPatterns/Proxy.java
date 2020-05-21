package designPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/19
 * @Time:15:55
 */
public class Proxy {

    public static void main(String[] args) {
        SingleLady singleLady = new BlackWidowStaticProxy();
        singleLady.getMarried();
        SingleLady blackWidow = new BlackWidow();
        SingleLady singleLady0 = (SingleLady) java.lang.reflect.Proxy.newProxyInstance(SingleLady.class.getClassLoader()
                , new Class[]{SingleLady.class} , new BlackWidowJdkProxyHandler(blackWidow));
        singleLady0.getMarried();
    }

}

interface SingleLady{
    void getMarried();
}

class BlackWidow implements SingleLady{
    @Override
    public void getMarried() {
        System.out.println("黑寡妇结婚了");
    }
}

class BlackWidowStaticProxy implements SingleLady{

    private SingleLady singleLady;
    public BlackWidowStaticProxy(){
        singleLady = new BlackWidow();
    }

    @Override
    public void getMarried() {
        System.out.println("在别人的静态介绍下");
        singleLady.getMarried();
    }
}

class BlackWidowJdkProxyHandler implements InvocationHandler{

    private Object object;
    public BlackWidowJdkProxyHandler(Object o){
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在别人的动态介绍下");
        Object result = method.invoke(object , args);
        return result;
    }
}
