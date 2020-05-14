package designPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/13
 * @Time:15:02
 */
public class Composite {

    public static void main(String[] args) {
        Company alibaba = new Alibaba(new ArrayList<>());
        Company taobao = new Taobao(new ArrayList<>());
        taobao.addSubCompanies(new TianMao(new ArrayList<>()));
        alibaba.addSubCompanies(new Mayijinfu(new ArrayList<>()));
        alibaba.addSubCompanies(taobao);
    }

}

interface Company{

    void addSubCompanies(Company company);

    List<Company> getSubCompanies();
}

class Alibaba implements Company{

    public String name = "阿里巴巴";
    private List<Company> subCompanies;
    public Alibaba(List<Company> subCompanies){
        this.subCompanies = subCompanies;
    }

    @Override
    public void addSubCompanies(Company company) {
        subCompanies.add(company);
    }

    @Override
    public List<Company> getSubCompanies() {
        return subCompanies;
    }
}

class Mayijinfu implements Company{

    public String name = "蚂蚁金服";
    private List<Company> subCompanies;
    public Mayijinfu(List<Company> subCompanies){
        this.subCompanies = subCompanies;
    }

    @Override
    public void addSubCompanies(Company company) {
        subCompanies.add(company);
    }

    @Override
    public List<Company> getSubCompanies() {
        return subCompanies;
    }
}

class Taobao implements Company{

    public String name = "淘宝";
    private List<Company> subCompanies;
    public Taobao(List<Company> subCompanies){
        this.subCompanies = subCompanies;
    }

    @Override
    public void addSubCompanies(Company company) {
        subCompanies.add(company);
    }

    @Override
    public List<Company> getSubCompanies() {
        return subCompanies;
    }
}

class TianMao implements Company{

    public String name = "天猫";
    private List<Company> subCompanies;
    public TianMao(List<Company> subCompanies){
        this.subCompanies = subCompanies;
    }

    @Override
    public void addSubCompanies(Company company) {
        subCompanies.add(company);
    }

    @Override
    public List<Company> getSubCompanies() {
        return subCompanies;
    }
}
