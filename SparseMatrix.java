import java.util.*;
class SparseMatrix { 

	// Maximum number of elements in matrix 
	int MAX = 100; 

	// Array representation 
	// of sparse matrix 
	//[][0] represents row 
	//[][1] represents col 
	//[][2] represents value 
//	int data[][] = new int[MAX][3]; 

	private final List<SparseElement> sparseData = new ArrayList<SparseElement>(MAX);
	// dimensions of matrix 
	int row, col; 

	// total number of elements in matrix 
	int len; 

	public SparseMatrix(int r, int c) 
	{ 

		// initialize row 
		row = r; 

		// initialize col 
		col = c; 

		// initialize length to 0 
		len = 0; 
	} 

	// insert elements into sparse matrix 
	public void insert(int r, int c, int val) 
	{ 

		// invalid entry 
		if (r > row || c > col) { 
			System.out.println("Wrong entry"); 
		} 

		else { 
			SparseElement newEle = new SparseElement(r,c,val);
			sparseData.add(newEle);
			// insert row value 
//			data[len][0] = r; 
//
//			// insert col value 
//			data[len][1] = c; 
//
//			// insert element's value 
//			data[len][2] = val; 

			// increment number of data in matrix 
			len++; 
		} 
	} 

	public void add(SparseMatrix b) 
	{ 

		// if matrices don't have same dimensions 
		if (row != b.row || col != b.col) { 
			System.out.println("Matrices can't be added"); 
		} 

		else { 

			int apos = 0, bpos = 0; 
			SparseMatrix result = new SparseMatrix(row, col); 

			while (apos < len && bpos < b.len) { 

				// if b's row and col is smaller 
				if(sparseData.get(apos).row > b.sparseData.get(bpos).row || (
						sparseData.get(apos).row == b.sparseData.get(bpos).row && 
						sparseData.get(apos).col > b.sparseData.get(bpos).col)){
//					System.out.println(b.sparseData.get(bpos).value + "a");
					// insert smaller value into result 
					result.insert(b.sparseData.get(bpos).row, 
							b.sparseData.get(bpos).col, 
							b.sparseData.get(bpos).value); 

					bpos++; 
				} 

				// if a's row and col is smaller 
				else if (sparseData.get(apos).row < b.sparseData.get(bpos).row ||
				(sparseData.get(apos).row == b.sparseData.get(bpos).row && 
						sparseData.get(apos).col < b.sparseData.get(bpos).col)) 

				{ 
//					System.out.println(sparseData.get(apos).value + "b");
					// insert smaller value into result 
					result.insert(sparseData.get(apos).row, 
							sparseData.get(apos).col, 
							sparseData.get(apos).value); 
					apos++; 
				} 

				else { 
//					System.out.println(sparseData.get(apos).value +" "  + b.sparseData.get(bpos).value+ "C");
					// add the values as row and col is same 
					int addedval = sparseData.get(apos).value + b.sparseData.get(bpos).value; 
//					System.out.println(addedval + "c");
					result.insert(sparseData.get(apos).row, 
							sparseData.get(apos).col, 
							addedval); 
					// then insert 
					apos++; 
					bpos++; 
				} 
			} 

			// insert remaining elements 
			while (apos < len) 
				result.insert(sparseData.get(apos).row, 
						sparseData.get(apos).col, 
						sparseData.get(apos).value); 

			while (bpos < b.len) 
				result.insert(b.sparseData.get(bpos).row, 
						b.sparseData.get(bpos).col, 
						b.sparseData.get(bpos).value);  

			// print result 
			result.print(); 
		} 
	} 
	public void sortData(){
		Collections.sort(sparseData);
	}

	public SparseMatrix transpose() 
	{ 

		// new matrix with inversed row X col 
		SparseMatrix result = new SparseMatrix(col, row); 

		// same number of elements 
		result.len = len; 

		// to count number of elements in each column 
		int count[] = new int[col + 1]; 

		// initialize all to 0 
		for (int i = 1; i <= col; i++) 
			count[i] = 0; 

		for (int i = 0; i < len; i++) 
			count[sparseData.get(i).col]++; 

		int[] index = new int[col + 1]; 

		// to count number of elements having col smaller 
		// than particular i 

		// as there is no col with value < 1 
		index[1] = 0; 

		// initialize rest of the indices 
		for (int i = 2; i <= col; i++) 

			index[i] = index[i - 1] + count[i - 1]; 

		for (int i = 0; i < len; i++) { 

			// insert a data at rpos and increment its value 
			int rpos = index[sparseData.get(i).col]++; 
//			System.out.print(rpos);
			// transpose row=col 
			int row1 = sparseData.get(i).col; 

			// transpose col=row 
			int col1 = sparseData.get(i).row; 

			// same value 
			int value1 = sparseData.get(i).value; 
			result.sparseData.add(new SparseElement(row1,col1,value1));
		} 
		Collections.sort(result.sparseData);
		// the above method ensures 
		// sorting of transpose matrix 
		// according to row-col value 
//		result.print();
		return result; 
	} 

	public void multiply(SparseMatrix b) 
	{ 

		if (col != b.row) { 

			// Invalid multiplication 
			System.out.println("Can't multiply, "
							+ "Invalid dimensions"); 

			return; 
		} 

		// transpose b to compare row 
		// and col values and to add them at the end 
		b = b.transpose(); 
		int apos, bpos; 

		// result matrix of dimension row X b.col 
		// however b has been transposed, hence row X b.row 
		SparseMatrix result = new SparseMatrix(row, b.row); 

		// iterate over all elements of A 
		for (apos = 0; apos < len;) { 
//			System.out.println("a");
			// current row of result matrix 
			int r = sparseData.get(apos).row; 

			// iterate over all elements of B 
			for (bpos = 0; bpos < b.len;) { 
//				System.out.println("b");
				// current column of result matrix 
				// data[][0] used as b is transposed 
				int c = b.sparseData.get(bpos).row;
//				int c = b.data[bpos][0]; 

				// temporary pointers created to add all 
				// multiplied values to obtain current 
				// element of result matrix 
				int tempa = apos; 
				int tempb = bpos; 

				int sum = 0; 

				// iterate over all elements with 
				// same row and col value 
				// to calculate result[r] 
				while (tempa < len && sparseData.get(tempa).row == r 
					&& tempb < b.len && b.sparseData.get(tempb).row == c) { 
//					System.out.println("c");
					if (sparseData.get(tempa).col < b.sparseData.get(tempb).col) 
						tempa++; 

					else if (sparseData.get(tempa).col > b.sparseData.get(tempb).col) 
						tempb++; 
					else
						sum += sparseData.get(tempa++).value * b.sparseData.get(tempb++).value; 
				} 

				// insert sum obtained in result[r] 
				// if its not equal to 0 
				if (sum != 0) 
					result.insert(r, c, sum); 

				while (bpos < b.len && b.sparseData.get(bpos).row == c) 
					bpos++; 
			} 

			while (apos < len && sparseData.get(apos).row == r) 
				apos++; 
		} 

		result.print(); 
	} 
	
	public boolean isSymmetric(){
		SparseMatrix transposed = this.transpose();
		print();
		transposed.print();
		for(int i=0;i<len;i++){
			if(sparseData.get(i).row == transposed.sparseData.get(i).row && sparseData.get(i).col == transposed.sparseData.get(i).col && sparseData.get(i).value == transposed.sparseData.get(i).value){
				continue;
			}
			else
				return false;
		}
		return true;
	}

	// printing matrix 
	public void print() 
	{ 
		System.out.println("Dimension: " + row + "x" + col); 
		System.out.println("Sparse Matrix: \nRow Column Value"); 

		for (int i = 0; i < len; i++) { 

			System.out.println(sparseData.get(i).row + " "
							+ sparseData.get(i).col + " " + sparseData.get(i).value); 
		} 
	} 

} 

 