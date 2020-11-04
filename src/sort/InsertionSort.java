package sort;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/4
 * @Time:14:15
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {3,5,45,35,11,14};

        doSort(arr);

        for (int a : arr){
            System.out.println(a);
        }
    }

    public static void doSort(int[] arr){
        for (int i=1; i<arr.length; i++){
            int temp = arr[i];
            for(int j=i-1; j>=0; j--){
                if(temp < arr[j]){
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }else {
                    break;
                }
            }
        }
    }
}
