package algorithm;

public class TwoDimensionalArray {

    public static void main(String[] args) {
        flipMatrix();
    }

    /**
     *将一个N*N的矩阵翻转90度
     */
    private static void flipMatrix(){
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,8,7,6},
                {5,4,3,2}
        };

        //获取数组的最大下标
        int maxIndex = matrix.length - 1;
        for(int i=0; i<matrix.length/2; i++){
            for(int j=i; j<maxIndex-i; j++){
                //递归旋转
                flipMatrixExchange(i , j , i , j , maxIndex , matrix , matrix[i][j]);
            }
        }

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void flipMatrixExchange(int i0 , int j0 , int i , int j , int maxIndex , int[][] matrix , int ex){
        int ni = j;
        int nj = maxIndex - i;
        int temp = matrix[ni][nj];
        matrix[ni][nj] = ex;
        if(ni == i0 && nj == j0){
            return;
        }else {

            flipMatrixExchange(i0 , j0 , ni , nj , maxIndex , matrix , temp);
        }
    }

}
