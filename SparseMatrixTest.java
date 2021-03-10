import static org.junit.Assert.*;
import org.junit.Test;

public class SparseMatrixTest {

	int[][] arr1 = {{5,0,3},
			{0,0,2},
			{0,1,0}};
	
	int[][] arr2 = {{1,0,0},
					{0,2,0},
					{0,0,1}};

	SparseMatrix sm1 = new SparseMatrix(arr1,3,3);
	SparseMatrix sm2 = new SparseMatrix(arr2,3,3);
	
	/**
	 * Test transpose method
	 */
	@Test
	public void testTranspose() {
		int[][] transpose1 = sm1.transposeMatrix();
		boolean check = true;
		for (int i = 0; i < transpose1.length; i++) {
			for (int j = 0; j < transpose1[0].length; j++) {
				if(arr1[i][j] != transpose1[i][j]){
					check = false;
					break;
				}
			}
		}
		assertEquals(false, check);
		int[][] transpose2 = sm2.transposeMatrix();
		check = true;
		for (int i = 0; i < transpose2.length; i++) {
			for (int j = 0; j < transpose2[0].length; j++) {
				if(arr2[i][j] != transpose2[i][j]){
					check = false;
					break;
				}
			}
		}
		assertEquals(true, check);
	}
	
	/*
	 * test for sparse matrix symmetry
	 */
	@Test
	public void testSymmetric() {
		assertEquals(false, sm1.isSymmetric());
		assertEquals(true, sm2.isSymmetric());
	}

	/*
	 * test for add sparse matrix
	 */
	@Test
	public void testAdd() {
		int[][] add = sm1.addMatrix(sm2);
		
		int[][] result = {{6,0,3},
				{0,2,2},
				{0,1,1}};
		assertArrayEquals(add, result);
	}
	

	/*
	 * test for multiply sparse matrix
	 */
	@Test
	public void testMultiply() {
		int[][] mul = sm1.multiplyMatrix(sm2);
		int[][] result = {{5,0,0},
				{0,2,0},
				{0,0,5}};
		assertArrayEquals(mul, result);
	}
}