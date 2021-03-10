import java.util.*;
class SparseMatrix { 



	//List to store all sparse elements
	private SparseElement[] sparseData;
	
	// dimensions of matrix 
	int row, col; 

	// total number of elements in matrix 
	int len; 

	//constructor
	public SparseMatrix(int[][] matrix,int r,int c) 
	{ 

		// initialize row 
		row = r; 

		// initialize col 
		col = c; 

		int count = 0,index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(matrix[i][j] !=0){
					count++;
				}
			}
		}
		
		len = count;
		
		sparseData = new SparseElement[count];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(matrix[i][j] !=0){
					SparseElement smi = new SparseElement(i, j, matrix[i][j]);
					sparseData[index++] = smi;
				}
			}
		}

	} 


	/**
	 * Function to add Two sparse matrix
	 * @param sparseMatrix another sparse matrix
	 */
	public int[][] addMatrix(SparseMatrix sparseMatrix) throws ArithmeticException{
		if(this.row != sparseMatrix.row 
				|| this.col != sparseMatrix.col){
			throw new ArithmeticException("Dimension are different");
		}
		int[][] addMat = new int[this.row][this.col];
		//initialize addMat with first matrix values
		for (int i = 0; i < this.len; i++) {
			addMat[this.sparseData[i].row][this.sparseData[i].col]
					= this.sparseData[i].value;
		}
		//add 2nd matrix to addMat
		for (int i = 0; i < sparseMatrix.len; i++) {
			addMat[sparseMatrix.sparseData[i].row][sparseMatrix.sparseData[i].col]
					+= sparseMatrix.sparseData[i].value;
		}
		return addMat;
	}
	
	
	
	//Function to Transpose sparse Matrix
	public int[][] transposeMatrix(){
		int[][] transpose = new int[row][col]; 
		for (int i = 0; i < len; i++) {
			transpose[this.sparseData[i].col][this.sparseData[i].row]
					= this.sparseData[i].value;
		}
		return transpose;
	}
	

	/**
	 * Function for multiplication of two sparse matrix
	 * @param b second matrix
	 */
	public int[][] multiplyMatrix(SparseMatrix sparseMatrix) throws ArithmeticException{
		if(this.col != sparseMatrix.row){
			throw new ArithmeticException("Dimension mismatch: this.column != sparsematrix.row");
		}
		int[][] mulMat = new int[this.row][sparseMatrix.col];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < sparseMatrix.len; j++) {
				if(this.sparseData[i].col == sparseMatrix.sparseData[j].row)
				mulMat[sparseMatrix.sparseData[j].row][this.sparseData[i].col]
						+=this.sparseData[i].value * sparseMatrix.sparseData[j].value;
			}
		}
		return mulMat;
	}
	
	//Function to check if matrix is boolean or not
	public boolean isSymmetric(){
		int[][] transposed = this.transposeMatrix();
		
		for(int i=0;i<len;i++){
			int rowCheck = sparseData[i].row;
			int colCheck = sparseData[i].col;
			if(transposed[rowCheck][colCheck] != sparseData[i].value)
				return false;
		}
		return true;
	}



} 

 