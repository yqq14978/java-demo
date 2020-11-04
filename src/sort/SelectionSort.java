package sort;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/4
 * @Time:11:09
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {17,45,87,45,65,4,5};

        for (int i=0; i<arr.length - 1; i++){
            for(int j=i+1; j<arr.length; j++){
                int temp = 0;
                if(arr[i] > arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int a: arr) {
            System.out.println(a);
        }
    }
}
