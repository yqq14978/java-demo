package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/8
 * @Time:9:45
 */
public class Factory {

    public static void main(String[] args) {
        User user1 = UserFactory.getUser(UserEnums.VIP_USER);
        user1.login();
        User user2 = UserFactory.getUser(UserEnums.COMMON_USER);
        user2.login();
    }

}

class UserFactory{
    public static User getUser(UserEnums userEnums){
        if(UserEnums.VIP_USER.equals(userEnums)){
            return new VipUser();
        }else if(UserEnums.COMMON_USER.equals(userEnums)){
            return new CommonUser();
        }
        return null;
    }
}

interface User{

    void login();

}

class CommonUser implements User{
    @Override
    public void login() {
        System.out.println("普通用户登陆成功");
    }
}

class VipUser implements User{
    @Override
    public void login() {
        System.out.println("VIP用户登陆成功");
    }
}

enum UserEnums{
    VIP_USER(),
    COMMON_USER();
}
