import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import map.SimpleHashMap;

class SolverTest {
	Solver s1;
	Solver s2;
	Solver s3;
	
	int[][] p1;
	int[][] p2;
	int[][] p3;
	
	@BeforeEach
	void setUp() {
		p1 = new int[9][9];  //create pregrid.
		for(int[] row: p1) {
			Arrays.fill(row, 0);
		}
		
		p2 = new int[9][9];  //create pregrid.
		for(int[] row: p2) {
			Arrays.fill(row, 0);
		}
		p3 = new int[9][9];  //create pregrid.
		for(int[] row: p3) {
			Arrays.fill(row, 0);
		}
		
	}

	@AfterEach
	void tearDown() {
		s1 = null;
		s2 = null;
		s3 = null;
		
		p1 = null;
		p2 = null;
		p3 = null;
	}
	
	@Test
	/**
	 * Just add some numbers to a pregrid.
	 */
	void test_init() {

		p1[0][3] = 7;
		p1[1][1] = 1;
		p1[2][7] = 3;
		p1[3][0] = 2;
		p1[2][8] = 9;
		
		s1 = new Solver(p1);
		
		assertTrue(s1.solve(), "should return true for solvable sudoku");

	}
	
	@Test
	/*
	 * Test a 'sudoku' which is not solvable.
	 */
	void test_unsolvable() {
		
		p1[1][0]=1;
		p1[0][3]=2;
		p1[2][2]=3;
		p1[3][0]=4;
		p1[0][5]=5;
		p1[1][1]=6;
		p1[0][1]=7;
		p1[0][2]=8;
		p1[2][1]=9;
		
		p2[0][4] = 1;
		p2[0][5] = 2;
		p2[1][3] = 3;
		p2[1][4] = 4;
		p2[1][5] = 5;
		p2[2][3] = 6;
		p2[2][4] = 7;
		p2[2][5] = 8;
		p2[3][3] = 9;
		/*
		p3[3][2] = 1;
		p3[3][4] = 2;
		p3[3][5] = 3;
		p3[4][3] = 4;
		p3[4][4] = 5;
		p3[4][5] = 6;
		p3[5][3] = 7;
		p3[5][4] = 8;
		p3[5][5] = 9;
		*/
		
		p3[0][0] = 1;
		p3[0][1] = 2;
		p3[1][0] = 3;
		p3[1][1] = 4;
		p3[1][2] = 5;
		p3[2][0] = 6;
		p3[2][1] = 7;
		p3[2][2] = 8;
		p3[3][2] = 9;
		
		s1 = new Solver(p1);
		s2 = new Solver(p2);
		s3 = new Solver(p3);
		
		assertFalse(s1.solve(), "should return false for unsolvable 'sudoku'");
		assertFalse(s2.solve(), "should return false for unsolvable 'sudoku'");
		assertFalse(s3.solve(), "should return false for unsolvable 'sudoku'");

		
	}
	
	@Test
	/**
	 * Test solving a real suduko.
	 */
	void test_real_sudoku() {
		
		p1[0][0] = 5;
		p1[0][1] = 3;
		p1[0][4] = 7;
		
		p1[1][0] = 6;
		p1[1][3] = 1;
		p1[1][4] = 9;
		p1[1][5] = 5;
		
		p1[2][1] = 9;
		p1[2][2] = 8;
		p1[2][7] = 6;
		
		p1[3][0] = 8;
		p1[3][4] = 6;
		p1[3][8] = 3;
		
		p1[4][0] = 4;
		p1[4][3] = 8;
		p1[4][5] = 3;
		p1[4][8] = 1;
		
		p1[5][0] = 7;
		p1[5][4] = 2;
		p1[5][8] = 6;
		
		p1[6][1] = 6;
		p1[6][6] = 2;
		p1[6][7] = 8;
		
		p1[7][3] = 4;
		p1[7][4] = 1;
		p1[7][5] = 9;
		p1[7][8] = 5;
		
		p1[8][4] = 8;
		p1[8][7] = 7;
		p1[8][8] = 9;
		
		s1 = new Solver(p1);
		
		assertTrue(s1.solve(),"Should return true for solvable sudoku.");
		
		
		for(int row=0; row <9;row+=1) {
			for(int col=0; col<9;col+=1) {
				int number = s1.getNumber(row, col);
				assertTrue(s1.trySetNumber(row,col,number),"Every number should be placable");
			}
		}
		
	}
	
	@Test
	/**
	 * Test passing a bad grid to the contructor (should throw illegalargument exception.
	 */
	void test_badgrid() {
		
		p1[0][0] = 1;
		p1[0][1] = 1;

		assertThrows(IllegalArgumentException.class, ()->{s1 = new Solver(p1);});
		
	}
	

}
