package designPatterns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/13
 * @Time:10:35
 */
public class Filter {

    public static void main(String[] args) {
        ArrayList<Food> foods1 = new ArrayList<>();
        foods1.add(new Food("蔬菜"));
        foods1.add(new Food("肉"));
        foods1.add(new Food("蔬菜"));
        foods1.add(new Food("肉"));
        ArrayList<Food> foods2 = new ArrayList<>();
        foods2.addAll(foods1);
        FoodFilter filter1 = new GreenFoodFilter();
        System.out.println(filter1.doFilter(foods1));
        FoodFilter filter2 = new MeatFood();
        System.out.println(filter2.doFilter(foods2));
    }

}

class Food{

    public String name;
    public Food(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }
}

interface FoodFilter{
    List<Food> doFilter(List<Food> foods);
}

class GreenFoodFilter implements FoodFilter{
    @Override
    public List<Food> doFilter(List<Food> foods) {
        Iterator<Food> iterator = foods.iterator();
        while (iterator.hasNext()){
            if(!iterator.next().name.equals("蔬菜")){
                iterator.remove();
            }
        }
        return foods;
    }
}

class MeatFood implements FoodFilter{
    @Override
    public List<Food> doFilter(List<Food> foods) {
        Iterator<Food> iterator = foods.iterator();
        while (iterator.hasNext()){
            if(!iterator.next().name.equals("肉")){
                iterator.remove();
            }
        }
        return foods;
    }
}
