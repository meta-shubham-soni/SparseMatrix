import java.util.*;

public class Main{
    public static void main(String args[]) 
	{ 
    	Scanner sc = new Scanner(System.in);
    	
    	int[][] arr1 = {{5,0,3},{0,0,2},{0,1,0}};
    	
    	int[][] arr2 = {{1,0,0},{0,2,0},{0,0,1}};
    	
    	SparseMatrix a = new SparseMatrix(arr1,3,3);
    	SparseMatrix b = new SparseMatrix(arr2,3,3);
    	
    	int[][] transposeOfa = a.transposeMatrix();
    	int[][] transposeOfb = b.transposeMatrix();
    	int[][] addResult = a.addMatrix(b);
    	int[][] mulResult = a.multiplyMatrix(b);
    	
    	System.out.println("Transpose Result (a)");
    	printMatrix(transposeOfa,3,3);
    	
    	System.out.println("Transpose Result (b)");
    	printMatrix(transposeOfb,3,3);
    	
    	System.out.println("Addition Result");
    	printMatrix(addResult,3,3);

    	System.out.println("Multiplication Result");
    	printMatrix(mulResult,3,3);

	}
    
    public static void printMatrix(int[][] matrix,int row,int col){
    	for(int i=0;i<row;i++){
    		for(int j=0;j<col;j++){
    			System.out.print(matrix[i][j]+ " ");
    		}
    		System.out.println();
    	}
    }
}
