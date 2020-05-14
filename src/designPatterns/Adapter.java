package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/12
 * @Time:14:45
 */
public class Adapter {

    public static void main(String[] args) {
        DataConnect dataConnect = new MyDataConnect();
        dataConnect.connect();
        DataConnect dataConnect2 = new DataConnectAdapter(new DataLog());
        dataConnect2.connect();
    }

}

interface DataConnect{

    void connect();

}

class MyDataConnect implements DataConnect{

    @Override
    public void connect() {
        System.out.println("连接MySql");
    }
}

interface Log{
    void info();
}

class DataLog implements Log{
    @Override
    public void info(){
        System.out.println("连接成功");
    }
}

class DataConnectAdapter implements DataConnect{

    private Log log;

    public DataConnectAdapter(Log log) {
        this.log = log;
    }

    @Override
    public void connect() {
        System.out.println("连接MySql");
        log.info();
    }
}
