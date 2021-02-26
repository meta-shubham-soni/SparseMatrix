
public class SparseElement implements Comparable<SparseElement> {
	int row;
	int col;
	int value;
	SparseElement(int r,int c,int val){
		row = r;
		col = c;
		value = val;
	}
	
	 @Override
	 public int compareTo(SparseElement o) {

	  if (o.row < this.row) {
	   return 1;
	  } else if (o.row > this.row) {
	   return -1;
	  }
	  else if(o.col < this.col)
		  return 1;
	  else if(o.col > this.col)
		  return -1;

	  return 0;

	 }
}
