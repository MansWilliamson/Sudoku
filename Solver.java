//package sudoku;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

//import javax.swing.border.EmptyBorder;
//import javax.swing.*; varför??

public class Solver implements SudokuSolver {
	private int[][] grid;
	private int[][] checkgrid;
	
	public Solver() {
		grid = new int[9][9];
		checkgrid = new int[9][9];
	}

	/**
	 * Starts a board with fixed values from pregrid. Also creates a @checkgrid which is used to lock positions and make them non-changeable.
	 * @param pregrid
	 * 
	 * 
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
		if(!is_ok()) {
			throw new IllegalArgumentException("Bad pregrid");
		}
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
				if(grid[i][j] == number & !(i==row & j==col))
					subcheck = false;
			}
		}
	   // Checks row;
		boolean rowcheck = true;
		for(int k=0;k<9;k++) {
			if(grid[row][k]==number & k!=col)
				rowcheck = false;
		}
		//checks column.
		boolean colcheck = true;
		for(int k=0;k<9;k++) {
			if(grid[k][col]==number &  k!=row)
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
	 * Returns true if solvable.
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
	private boolean solve_help(int row, int col) {
		if(row==9) {
			return true;
		}
		
		if(checkgrid[row][col]!=0) {
			if(trySetNumber(row,col,checkgrid[row][col])) { //Kolla om talet som redan står där är ok.
				int col_next = (col+1)%9;
				int row_next =row;
				if(col_next==0) {
					row_next +=1;
				}
				return solve_help(row_next,col_next);
			} else {
				return false;
			}
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
	 * Returns the numbers entered in the sudoku.
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
		//checkgrid= new int[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				int temp=numbers[i][j];
				checkgrid[i][j]=temp;
				grid[i][j] = temp;
			}
		}
		
	}
	/**
	 * Prints the sudoku.
	 */
	public void print() {
		for(int[] row: this.grid) {
			System.out.println(Arrays.toString(row));
		}
	}	
	/**
	 * Prints the checkgrid.
	 */
	public void checkgrid() {
		for(int[] row: this.checkgrid) {
			System.out.println(Arrays.toString(row));
		}
	}	
	/**
	 * Check if numbers entered in the grid are allowed.
	 * @param pregrid
	 * @return
	 */
	public boolean is_ok() {
		for(int row=0; row <9;row+=1) {
			for(int col=0; col<9;col+=1) {
				int number = getNumber(row, col);
				if(!trySetNumber(row,col,number) & number!=0)
					return false;

			}
		}
		return true;
	}

	
}
