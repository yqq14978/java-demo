package algorithm;

public class TwoDimensionalArray {

    public static void main(String[] args) {
//        flipMatrix();
//        zeroMatrix();
        zeroMatrix1();
    }

    /**
     * 用矩阵中的第一列与第一行分别记录下出现过0的行和列，然后用一个变量flag来标记第一列是否出现过0
     */
    private static void zeroMatrix1(){
        int[][] matrix = {
                {2,3,4},
                {0,1,0},
                {9,3,0},
                {7,8,9}};
        boolean flag = false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i=0; i<m; i++){
            //标记第一列是否出现0
            if(matrix[i][0] == 0){
                flag = true;
            }
            for (int j=1; j<n; j++){
                //如果遍历到0，将它对应的行与列标记为0
                if(matrix[i][j] == 0){
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        //为了保证每一列的第一个元素不要提前被更新，所以需要从最后一行开始更新，为了保护第一列的数据，从每一行的第二列开始处理
        for(int i=m-1; i>=0; i--){
            for(int j=1; j<n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
            if(flag){
                matrix[i][0] = 0;
            }
        }
        foreach(matrix);
    }

    /**
     * 零矩阵，找到矩阵中存在的0元素，并将其所在的行和列的所有元素置为0（错误示范，无法检测到同一行或者同一列存在多个0的情况）
     */
    private static void zeroMatrix(){
        int[][] matrix = {
                {2,3,4},
                {0,1,5},
                {9,3,0},
                {7,8,9}};

        int m = matrix.length;
        int n = matrix[0].length;
        int m0 = 0;
        int n0 = 0;
        getZero(m0 , n0 , m , n , matrix);
        //遍历结果
        foreach(matrix);
    }

    private static void getZero(int m0 , int n0 , int m , int n , int[][] matrix){
        for(int i = m0; i<m; i++){
            for(int j = n0; j<n; j++){
                if(matrix[i][j] == 0){
                    //将所在的行和列清零
                    zero(i , j , matrix);
                    //清零之后会划分出未查找的两片分区，将这两片分区进行递归查找
                    getZero(i+1 , j+1 , m , n , matrix);
                    if(j > 0){
                        getZero(i+1 , n0 , m , j , matrix);
                    }
                    return;
                }
            }
        }
    }

    private static void zero(int m , int n , int[][] matrix){
        for(int i=0; i<matrix[0].length; i++){
            matrix[m][i] = 0;
        }
        for(int j=0; j<matrix.length; j++){
            matrix[j][n] = 0;
        }
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
        foreach(matrix);
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

    private static void foreach(int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

}
