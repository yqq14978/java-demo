package sort;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2021/1/25
 * @Time:14:02
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1,13,3,42,4,24,2};

        doSort(arr , 0 , arr.length - 1);

        for (int a : arr){
            System.out.println(a);
        }
    }

    private static void doSort(int[] arr , int leftIndex , int rightIndex){
        if(rightIndex - leftIndex <= 0){
            return;
        }
        int i = leftIndex;
        int j = rightIndex;
        int x = arr[i];
        //正在进行左排还是右排的标识
        boolean swap = true;
        while (i < j){
            if(swap){
                //进行左排
                if(x > arr[j]){
                    arr[i] = arr[j];
                    swap = false;
                    i++;
                }else {
                    j--;
                }
            }else {
                //进行右排
                if(x <= arr[i]){
                    arr[j] = arr[i];
                    swap = true;
                    j--;
                }else {
                    i++;
                }
            }
        }
        arr[i] = x;
        doSort(arr , leftIndex , i - 1);
        doSort(arr , i + 1 , rightIndex);
    }
}
