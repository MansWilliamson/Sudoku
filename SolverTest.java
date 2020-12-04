import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import map.SimpleHashMap;

class SolverTest {
	Solver s;
	int[][] p;
	
	@BeforeEach
	void setUp() {
		p = new int[9][9];  //create pregrid.
		for(int[] row: p) {
			Arrays.fill(row, 0);
		}
		
	}

	@AfterEach
	void tearDown() {
		s = null;
		p = null;
	}
	
	@Test
	/**
	 * Just add some numbers to a pregrid.
	 */
	void test_init() {

		p[0][3] = 7;
		p[1][1] = 1;
		p[2][7] =3;
		p[3][0] = 2;
		p[2][8] = 9;
		
		s = new Solver(p);
		
		assertTrue(s.solve(), "should return true for solvable sudoku");
		s = null;
		p=null;
	}
	
	@Test
	/*
	 * Test a 'sudoku' which is not solvable.
	 */
	void test_unsolvable() {
		
		p[1][0]=1;
		p[0][3]=2;
		p[2][2]=3;
		p[3][0]=4;
		p[0][5]=5;
		p[1][1]=6;
		p[0][1]=7;
		p[0][2]=8;
		p[2][1]=9;
		
		s = new Solver(p);
		
		assertFalse(s.solve(), "should return false for unsolvable 'sudoku'");
		s = null;
		p=null;		
		
	}
	
	@Test
	/**
	 * Test solving a real suduko.
	 */
	void test_real_sudoku() {
		
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
		
		s = new Solver(p);
		
		assertTrue(s.solve(),"Should return true for solvable sudoku.");
		
	}
	

}
