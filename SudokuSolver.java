//package sudoku;



public interface SudokuSolver {
	/**
	 * Sets the digit number in the box row, col.
	 * 
	 * @param row    The row
	 * @param col    The column
	 * @param number The digit to insert in row, col
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	void setNumber(int row, int col, int number);

	/**
	 * Checks if number can be inserted in [row, col], if not - returns false.
	 * 
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	boolean trySetNumber(int row, int col, int number);

	/**
	 * Returns number of row and col.
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	int getNumber(int row, int col);

	/**
	 * Removes number on [row, col].
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	void removeNumber(int row, int col);

	/**
	 * Empties the whole sudoku
	 */
	void clear();

	/**
	 * Returns true of solvable
	 */
	boolean solve();

	/**
	 * Returns numbers in sudoku.
	 */
	int[][] getNumbers();

	/**
	 * Fyller i siffrorna i numbers i sudokut.
	 * 
	 * @throws IllegalArgumentException if not all numbers in [0..9]
	 **/
	void setNumbers(int[][] numbers);
}

/*
 * TBD: Skriva javadoc-kommentarer (PÃ¥bÃ¶rjat, kommentaren till setNumber Ã¤r klar.)
 *  Kommentarerna ska vara pÃ¥ ett sprÃ¥k (gÃ¤rna engelska, annars svenska) och fÃ¶lja 
 *  konventionen fÃ¶r javadoc-kommentarer.
 */