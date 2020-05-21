package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/20
 * @Time:15:33
 */
public class ChainOfResponsibility {
}

interface Approve{

    void doApprove(String message);

    boolean isRun(String type);

}


