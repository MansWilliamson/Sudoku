//package sudoku;

import java.util.Arrays;

//import javax.swing.border.EmptyBorder;
//import javax.swing.*; varför??

public class Solver implements SudokuSolver {
	int[][] grid;
	int[][] checkgrid;

	/**
	 * Starts a board with fixed values from pregrid. Also creates a @checkgrid which is used to lock positions and make them non-changeable.
	 * @param pregrid
	 */
	public Solver(int[][] pregrid) {
		// TODO Auto-generated constructor stub
		grid=pregrid;
		checkgrid= new int[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				int temp=pregrid[i][j];
				checkgrid[i][j]=temp;
			}
		}
		/*
		for(int[] row: grid) {
			Arrays.fill(row, 0);
		}*/
	}
	/**
	 * Sets the digit number in the box row, col. Should be used together with trySetNumber!
	 * 
	 * @param row    The row
	 * @param col    The column
	 * @param number The digit to insert in row, col
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	@Override
	public void setNumber(int row, int col, int number) {
		// TODO Auto-generated method stub
		grid[row][col]=number;
	}
	/**
	 * Checks if number can be inserted in [row, col], if not - returns false.
	 * 
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	@Override
	public boolean trySetNumber(int row, int col, int number) {
		// TODO Auto-generated method stub
		//Checks submatrix
		/*
		if(checkgrid[row][col]!=0) {
			return false;
		}*/
		int stop_row = -1;
		int start_row =-1;
		int start_col =-1;
		int stop_col=-1;
		boolean subcheck = true;
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
				if(grid[i][j] == number)
					subcheck = false;
			}
		}
	   // Checks row;
		boolean rowcheck = true;
		for(int k=0;k<9;k++) {
			if(grid[row][k]==number)
				rowcheck = false;
		}
		//checks column.
		boolean colcheck = true;
		for(int k=0;k<9;k++) {
			if(grid[k][col]==number)
				colcheck = false;
		}
		return subcheck && rowcheck && colcheck;
	}
	
	
	
	/**
	 * Returns number of row and col.
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	@Override
	public int getNumber(int row, int col) {
		// TODO Auto-generated method stub
		return grid[row][col];
	}
	/**
	 * Removes number on [row, col].
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	@Override
	public void removeNumber(int row, int col) {
		// TODO Auto-generated method stub
		if(checkgrid[row][col]==0) {
			grid[row][col]=0;
		}
	}
	/**
	 * Empties the whole sudoku
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int[] row: grid) {
			Arrays.fill(row, 0);
		}
		
	}
	/**
	 * Returns true if solvable
	 */
	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		return solve_help(0,0);
	}

	/**
	 * Help method for the solve() method. Solve sudoku starting at (row,col).
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean solve_help(int row, int col) {
		if(row==9) {
			return true;
		}
		
		if(checkgrid[row][col]!=0) {
			int col_next = (col+1)%9;
			int row_next =row;
			if(col_next==0) {
				row_next +=1;
			}
			return solve_help(row_next,col_next);
		}
		for(int val=1;val<10;val++){
			if(trySetNumber(row,col,val)) {
				setNumber(row,col,val);
				int col_next = (col+1)%9;
				int row_next =row;
				if(col_next==0) {
					row_next +=1;
				}
				if(solve_help(row_next,col_next)) {
					return true;
				}
				removeNumber(row,col);
			}	
		}
		return false;
	}
	
	/**
	 * Returns numbers in sudoku.
	 */
	@Override
	public int[][] getNumbers() {
		// TODO Auto-generated method stub
		return this.grid;
	}
	/**
	 * Fyller i siffrorna i numbers i sudokut.
	 * 
	 * @throws IllegalArgumentException if not all numbers in [0..9]
	 **/
	@Override
	public void setNumbers(int[][] numbers) {
		// TODO Auto-generated method stub
		
	}

	public void print() {
		for(int[] row: this.grid) {
			System.out.println(Arrays.toString(row));
		}
	}	
	
	public void checkgrid() {
		for(int[] row: this.checkgrid) {
			System.out.println(Arrays.toString(row));
		}
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] p = new int[9][9];  //create pregrid.
		for(int[] row: p) {
			Arrays.fill(row, 0);
		}
		
		/*
		p[0][0]=1;
		p[0][1]=1;
		*/
		
		/*
		p[0][3] = 7;
		p[1][1] = 1;
		p[2][7] =3;
		
		p[3][0] = 2;
		p[2][8] = 9;
		*/
		
		/*
		p[1][0]=1;
		p[0][3]=2;
		p[2][2]=3;
		p[3][0]=4;
		p[0][5]=5;
		p[1][1]=6;
		p[0][1]=7;
		p[0][2]=8;
		p[2][1]=9;
		*/
		
		p[0][0] = 5;
		p[0][1] = 3;
		p[0][4] = 7;
		
		p[1][0] = 6;
		p[1][3] = 1;
		p[1][4] = 9;
		p[1][5] = 5;
		
		p[2][1] = 9;
		p[2][2] = 8;
		p[2][7] = 6;
		
		p[3][0] = 8;
		p[3][4] = 6;
		p[3][8] = 3;
		
		p[4][0] = 4;
		p[4][3] = 8;
		p[4][5] = 3;
		p[4][8] = 1;
		
		p[5][0] = 7;
		p[5][4] = 2;
		p[5][8] = 6;
		
		p[6][1] = 6;
		p[6][6] = 2;
		p[6][7] = 8;
		
		p[7][3] = 4;
		p[7][4] = 1;
		p[7][5] = 9;
		p[7][8] = 5;
		
		p[8][4] = 8;
		p[8][7] = 7;
		p[8][8] = 9;
		
		Solver s = new Solver(p);
		
		//s.print();
		
		
		System.out.println(s.solve());
		s.print();
		
		
	}
}
