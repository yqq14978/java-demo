package sort;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/11/4
 * @Time:10:08
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int[] arr = {32 ,12,45,2,12,32,4};
        int[] arr = {1,2,3,4,5,6};

        for (int i=0; i<arr.length; i++){
            boolean didswap = false;
            for(int j=0; j<arr.length - i - 1; j++){
                int temp = 0;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    didswap = true;
                }
            }
            if(!didswap){
                break;
            }
        }

        for(int a : arr){
            System.out.println(a);
        }
    }

}
