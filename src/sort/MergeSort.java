package sort;

import java.util.Arrays;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/4
 * @Time:16:34
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {213,3,13,34,54,3,2};
        for (int a : doMerge(arr)){
            System.out.println(a);
        }
    }

    public static int[] doMerge(int[] arr){
        if(arr.length < 2){
            return arr;
        }
        int mid = arr.length / 2;
        int[] leftArr = Arrays.copyOfRange(arr , 0 , mid);
        int[] rightArr = Arrays.copyOfRange(arr , mid , arr.length);
        //合并两个数组
        return merge(doMerge(leftArr) , doMerge(rightArr));
    }

    public static int[] merge(int[] arr1 , int[] arr2){
        int[] newArr = {arr1.length + arr2.length};
        int left = 0;
        int right = 0;
        for(int i=0; i<newArr.length; i++){
            if(left >= arr1.length){
                newArr[i] = arr2[right];
                right++;
            }else if(right >= arr2.length){
                newArr[i] = arr1[left];
                left++;
            }else if(arr1[left] > arr2[right]){
                newArr[i] = arr2[right];
                right++;
            }else {
                newArr[i] = arr1[left];
                left++;
            }
        }
        return newArr;
    }
}
