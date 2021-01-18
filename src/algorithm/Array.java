package algorithm;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2021/1/18
 * @Time:11:33
 */
public class Array {

    public static void main(String[] args) {
        removeDuplicates();
    }

    /**
    * @Author yeqq
    * @Description 计算数组中不重复的数有多少个
    * @Date 11:43 2021/1/18
    * @Param [nums]
    * @return int
    **/
    public static void removeDuplicates(){
        int[] nums = {1,1,2,2,3,3,4,5,5};
        int i = 0;
        for (int j = 1; j < nums.length ; j++){
            if(nums[i] != nums[j]){
                if(j - i > 1){
                    nums[i+1] = nums[j];
                }
                i++;
            }
        }
        System.out.println(i + 1);
    }
}
