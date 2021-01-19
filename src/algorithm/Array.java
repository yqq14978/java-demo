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
//        removeDuplicates();
//        pivotIndex();
//        searchInsert();
        merge();
        System.exit(0);
    }

    /**
    * @Author yeqq
    * @Description 计算数组中不重复的数有多少个（快慢指针）
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

    /**
    * @Author yeqq
    * @Description 寻找数组的中心索引，即该索引左边所有元素的和等于右边所有元素的和，没有中心索引则返回-1
    * @Date 10:50 2021/1/19
    * @Param []
    * @return void
    **/
    public static void pivotIndex(){
        int[] nums = {1,7,3,6,5,6};
        int sum = 0;
        int pivotIndex = -1;
        int leftCount = 0;
        for (int x: nums) {
            sum += x;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (leftCount == sum - leftCount - nums[i]){
                pivotIndex = i;
                break;
            }
            leftCount += nums[i];
        }
        System.out.println("右边元素和：" + leftCount + "——左边元素和：" + leftCount);
        System.out.println("中心索引：" + pivotIndex);
    }
    
    /**
    * @Author yeqq
    * @Description 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中
    * ，返回它将会被按顺序插入的位置。(最优解，二分查找)
    * @Date 14:23 2021/1/19
    * @Param []
    * @return void
    **/
    public static void searchInsert(){
        int[] nums = {1,3,5,6};
        int target = 5;
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right){
            mid = (left + right) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] == target){
                System.out.println(mid);
                return;
            }
        }
        System.out.println(left);
//        int midIndex = nums.length / 2;
//        int targetIndex = -1;
//        if(target > nums[midIndex]){
//            for(int i = midIndex + 1; i < nums.length; i++){
//                if(target < nums[i]){
//                    targetIndex = i;
//                    break;
//                }else if(target == nums[i]){
//                    targetIndex = i;
//                    break;
//                }
//            }
//            if(targetIndex == -1){
//                targetIndex = nums.length;
//            }
//        }else if(target < nums[midIndex]){
//            for(int i = midIndex - 1; i >= 0; i--){
//                if(target > nums[i]){
//                    targetIndex = i + 1;
//                    break;
//                }else if(target == nums[i]){
//                    targetIndex = i;
//                    break;
//                }
//            }
//            if(targetIndex == -1){
//                targetIndex = 0;
//            }
//        }else {
//            targetIndex = midIndex;
//        }
//        System.out.println(targetIndex);
    }

    /**
    * @Author yeqq
    * @Description 合并重叠的区间
    * @Date 16:53 2021/1/19
    * @Param []
    * @return void
    **/
    public static void merge(){
        int[][] intervals = {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        //先对已有区间进行排序
        for (int i=0; i<intervals.length; i++){
            boolean didswap = false;
            for(int j=0; j<intervals.length - i - 1; j++){
                int[] temp = {};
                if(intervals[j][0] > intervals[j+1][0]){
                    temp = intervals[j];
                    intervals[j] = intervals[j+1];
                    intervals[j+1] = temp;
                    didswap = true;
                }
            }
            if(!didswap){
                break;
            }
        }
        int mergeTimes = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i - 1][1] >= intervals[i][0]){
                if(intervals[i - 1][1] < intervals[i][1]){
                    intervals[i - 1][1] = intervals[i][1];
                }
                intervals[i] = intervals[i - 1];
                mergeTimes++;
            }
        }
        int[][] newIntervals = new int[intervals.length - mergeTimes][2];
        int i = 0;
        for (; mergeTimes < intervals.length; mergeTimes++){
            newIntervals[i] = intervals[mergeTimes];
            System.out.println("[" + newIntervals[i][0] + "," + newIntervals[i][1] + "]");
            i++;
        }
    }
}
