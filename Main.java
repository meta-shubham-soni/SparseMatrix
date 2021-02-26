import java.util.*;

public class Main{
    public static void main(String args[]) 
	{ 
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Please enter row and col of 1st matrix");
    	int r1 = sc.nextInt();
    	int c1 = sc.nextInt();
    	System.out.println("Please enter row and col of 2st matrix");
    	int r2 = sc.nextInt();
    	int c2 = sc.nextInt();
    	SparseMatrix a = new SparseMatrix(r1, c1); 
    	SparseMatrix b = new SparseMatrix(r2, c2); 
    	
    	System.out.println("Please enter elements of 1st matrix");
    	System.out.println("Please enter -1 to exit");

    	while(true){
    		int r = sc.nextInt();
    		if(r == -1)
    			break;
    		else{
    			int c = sc.nextInt();
    			int val = sc.nextInt();
    			a.insert(r, c, val);
    		}
    	}
    	a.sortData();
    	
    	System.out.println("Please enter elements of 2st matrix");
    	System.out.println("Please enter -1 to exit");

    	while(true){
    		int r = sc.nextInt();
    		if(r == -1)
    			break;
    		else{
    			int c = sc.nextInt();
    			int val = sc.nextInt();
    			b.insert(r, c, val);
    		}
    	}
    	
    	b.sortData();
		

		// Output result 
		System.out.println("Addition: "); 
		a.add(b); 
		System.out.println("\nMultiplication: "); 
		a.multiply(b); 

		SparseMatrix atranspose = a.transpose(); 
		atranspose.print(); 
		
		System.out.println("\nis A symmetric: " + a.isSymmetric());
		System.out.println("\nis B symmetric: " + b.isSymmetric());
	}
}
