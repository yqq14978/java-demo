package sort;

/**
 * Created with IDEA
 *
 * 根据增量gap将原来的数组分成若干个小数组，然后再对这些数组进行插入排序，直到增量gap变为1
 *
 * @author:yeqq
 * @Date:2020/11/4
 * @Time:15:02
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {1,13,3,42,4,24,2};
//        int gap = arr.length/2;
//
//        while (true){
//            for(int i=0; i < arr.length/gap; i++){
//                if(i == arr.length - 1){
//                    for (int j=i*(arr.length/gap) + 1; j<arr.length; j++){
//                        int temp = arr[j];
//                        for(int k=j-1; k>=i*(arr.length/gap); k--){
//                            if(temp < arr[k]){
//                                arr[k+1] = arr[k];
//                                arr[k] = temp;
//                            }else {
//                                break;
//                            }
//                        }
//                    }
//                }else {
//                    for (int j=i*(arr.length/gap) + 1; j<(i+1)*(arr.length/gap); j++){
//                        int temp = arr[j];
//                        for(int k=j-1; k>=i*(arr.length/gap); k--){
//                            if(temp < arr[k]){
//                                arr[k+1] = arr[k];
//                                arr[k] = temp;
//                            }else {
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//            gap /= 2;
//            if(gap == 1){
//                InsertionSort.doSort(arr);
//                break;
//            }
//        }

        doSort(arr);

        for (int a : arr){
            System.out.println(a);
        }
    }

    public static void doSort(int[] arr){
        int len = arr.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }
}
