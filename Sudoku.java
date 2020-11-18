
import java.util.Arrays;

public class Sudoku {

	int[][] grid;

	public Sudoku() {
		grid = new int[9][9];
		for(int[] row: grid) {
			Arrays.fill(row, 0);
		}
	}
	
	public boolean Solver() {
		return solve(0,0);
	}
	
	public boolean solve(int row,int col){
		if(row==9) {
			return true;
		}
		for(int val=1;val<10;val++){
			if(place(val,row,col)) {
				int col_next = (col+1)%9;
				int row_next =row;
				if(col_next==0) {
					row_next +=1;
				}
				if(solve(row_next,col_next)) {
					return true;
				}
				remove(row,col);
			}	
		}
		return false;
	}
	

	
	public boolean place(int val, int row, int col) {
		if(is_placable(val,row,col)) {
			grid[row][col] = val;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void remove(int row,int col) {
		this.grid[row][col] = 0;
	}

	//Check if it is possible to place val at position (row,col).
	public boolean is_placable(int val, int row, int col) {
		boolean placable=true;
		if(SubMatrixContains(val,row,col)){
			placable = false;
		}
		if(checkRow(val,row,col)){
			placable = false;
		}
		if(checkCol(val,row,col)) {
			placable = false;
		}
		return placable;
	}
	
	public void print() {
		for(int[] row: this.grid) {
			System.out.println(Arrays.toString(row));
		}
	}
	

	//Check if submatrix that m[row][col] belongs to already contains the value
	// that we want to place at position (row,col).
	public boolean SubMatrixContains(int val,int row,int col) {
		int stop_row = -1;
		int start_row =-1;
		int start_col =-1;
		int stop_col=-1;
		boolean found = false;
		for(int k=0;k<9;k=k+3) {
			if(k<=row & row<k+3) {  //find row-indices for sub-matrix.
				start_row = k;
				stop_row = k+3;
			}
			if(k<=col & col<k+3) { //find column-indices for sub-matrix.
				start_col = k;
				stop_col = k+3;
			}
		} 
		for(int i = start_row; i<stop_row; i++) {
			for(int j=start_col; j<stop_col;j++) {
				if(this.grid[i][j] == val)
					found = true;
			}
		}
	    return found;
	}
	//Check if the row where (row,col) is located contains val.
	public boolean checkRow(int val, int row, int col) {  
		boolean found = false;
		for(int k=0;k<9;k++) {
			if(this.grid[row][k]==val)
				found = true;
		}
		return found;
	}
	//Check if the column where (row,col) is located contains val.
	public boolean checkCol(int val,int row, int col) {
		boolean found = false;
		for(int k=0;k<9;k++) {
			if(this.grid[k][col]==val)
				found = true;
		}
		return found;
	}
	
	
	public static void main(String[] args){
		Sudoku s = new Sudoku();
		
		
		s.place(1, 1, 1);
		s.place(1, 1, 4);
		s.place(3, 1, 7);
		
		s.place(2, 4, 1);
		s.place(3, 4, 4);
		s.place(1, 4, 7);
		
		s.place(3, 7, 1);
		s.place(1, 7, 4);
		s.place(2, 7, 7);
		

		
		System.out.println(s.Solver());
		
		s.print();
		
		//Create unsolvable "Sudoku"; row 3 column 5 (with indices starting at 0) has no solution.
		
		/*
		s.place(1, 2, 5);
		s.place(2, 3, 2);
		s.place(3, 6, 5);
		s.place(4, 4, 5);
		s.place(5, 3, 0);
		s.place(6, 3, 3);
		s.place(7, 3, 8);
		s.place(8, 5, 5);
		s.place(9, 5,4);
		
		s.Solver();
		*/
		
		/*
		//Cannot place anything on the first square (0,0):
		s.place(1, 2, 2);
		s.place(2, 0, 3);
		s.place(3, 3, 0);
		s.place(4, 1, 1);
		s.place(5, 0, 4);
		s.place(6, 4, 0);
		s.place(7, 0, 5);
		s.place(8, 5, 0);
		s.place(9, 2, 1);
		
		s.print();
		System.out.println(s.Solver());
		
		
		//s.print();
		*/
		
		
		//Riktigt sudoku:
		
		/*
		s.place(7, 0, 1);
		s.place(9, 0, 2);
		s.place(2, 0, 4);
		s.place(4, 0, 5);
		s.place(8, 0, 6);
		s.place(1, 0, 7);
		
		s.place(5, 1,0);
		s.place(4, 1,2);
		s.place(7, 1,4);
		s.place(8, 1,5);
		s.place(2, 1,7);
		
		s.place(6, 2, 0);
		s.place(9, 2, 6);
		s.place(7, 2, 8);
		
		s.place(4, 3, 0);
		s.place(3, 3, 2);
		s.place(2, 3, 3);
		s.place(8, 3, 4);
		s.place(6, 3, 5);
		s.place(1, 3, 8);
		
		s.place(8, 4, 1);
		s.place(2, 4, 2);
		s.place(3, 4, 6);
		s.place(6, 4, 8);
		
		s.place(6, 5, 1);
		s.place(1, 5, 3);
		s.place(3, 5, 4);
		s.place(2, 5, 8);
		
		s.place(4, 6, 3);
		s.place(2, 6, 5);
		s.place(8, 6, 8);
		
		s.place(4, 7, 1);
		s.place(6, 7, 2);
		s.place(8, 7, 3);
		s.place(9, 7, 8);
		
		s.place(1, 8, 5);
		s.place(2, 8, 6);
		s.place(7, 8, 7);
		
		System.out.println(s.solve(0,0));
		s.print();
		*/
	}
	
}

