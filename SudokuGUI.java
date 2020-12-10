
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SudokuGUI {
	

	public static void main(String[] args){
		//Grejer för sudokulösaren
		int[][] pregrid= new int[9][9]; //pregrid till Sudokulösaren
		
		
		//Använd Swing
		// fönster med klickbara grids, +1 varje klick per ruta
		//generate solution-knapp - skickar nuvarande "pregrid" till SudokuSolver(pregrid)
		//får fönster om icke-lösbar
		//fönster med lösning om möjlig.
		
		
		String title= "Sudoku Solver 300";
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();		
		// pane är en behållarkomponent till vilken de övriga komponenterna(listvy, knappar etc.) ska läggas till.
		/**	
		 * Adds panels and sets Layout
		 */
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new GridLayout(9,9));
		centerPanel.setPreferredSize(new Dimension(500,500));
		pane.add(centerPanel,BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(200, 100));
		southPanel.setBorder(new EmptyBorder(5,10,5,10));
		pane.add(southPanel, BorderLayout.SOUTH);
		/**	
		 * add panel with buttons
		 */
		//Skapar en tom board.
		//reset(centerPanel, pregrid);

		/**	
		 * add Solve button + clear button
		 */	
		//solve button
		JButton solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				//solve
				//uppdatera pregrid till värdena på 9x9 buttonsen och skicka in i solvern.
				Solver solver = new Solver(pregrid);
				solver.solve();
				
				for(int[] row: pregrid) {
					System.out.println(Arrays.toString(row));
				}
				/*
				for(int i=0;i<9;i++){
					for(int j=0;j<9;j++) {
						buttongrid[i][j].setText(solver.getNumber(i,j));
			}*/
		}
		});		
		southPanel.add(solveButton);
		
		//clear button
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				//clear
				//set all button-values to 0
				//reset(centerPanel,pregrid);
				centerPanel.updateUI();
			}
		});	
		southPanel.add(clearButton);
		
		
		//Starta
		frame.pack();
		frame.setVisible(true);
	
	
	JButton[][] buttongrid=new JButton[9][9];
	for(int i=0;i<9;i++){
		for(int j=0;j<9;j++) {
			buttongrid[i][j]= new JButton("0");
			JButton tempButton=buttongrid[i][j];
			if((i/3+j/3)%2==0) {
				buttongrid[i][j].setBackground(Color.lightGray);
			}else {
				buttongrid[i][j].setBackground(Color.WHITE);
			}
			final Integer inneri = new Integer(i);
			final Integer innerj = new Integer(j);  //Verkar som att detta är nödvändigt för att komma åt index:n i actionlistener:n.
			buttongrid[i][j].addActionListener(new ActionListener() {
				int val=0;
		        public void actionPerformed(ActionEvent ae) {
		        	 	//Här händer saker när man trycker på knappen
		        	 	//Gör en pregrid där dessa värden även sätts in, denna pregrid skickas sedan in i solve.
		        	 	val=val+1;
		        	 	if(val>9) {
		        	 		val=0;
		        	 	}
		        	 	tempButton.setText(String.valueOf(val));
		        	 	pregrid[inneri][innerj] = val;
		        	 	System.out.println("p["+inneri+","+innerj+"] = "+val);
		          }
		       });
			centerPanel.add(buttongrid[i][j]);
		}
	}
	centerPanel.updateUI();
}

}
	/**
	 * Ställer tillbaka alla knappar till 0. (ska den göra det med pregrid också?)
	 * @param panel
	 * @param pregrid
	 */
	/*
	private static void reset(JPanel panel, int[][] pregrid) {
		panel.removeAll();
		JButton[][] buttongrid=new JButton[9][9];
		//Knappar, hur sätter vi in 81 st??
		//kanske göra "subgridsen" till olika färger?
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++) {
				buttongrid[i][j]= new JButton("0");
				JButton tempButton=buttongrid[i][j];
				if((i/3+j/3)%2==0) {
					buttongrid[i][j].setBackground(Color.lightGray);
				}else {
					buttongrid[i][j].setBackground(Color.WHITE);
				}
				buttongrid[i][j].addActionListener(new ActionListener() {
					int val=0;
			         public void actionPerformed(ActionEvent ae) {
			        	 	//Här händer saker när man trycker på knappen
			        	 	//Gör en pregrid där dessa värden även sätts in, denna pregrid skickas sedan in i solve.
			        	 	val=val+1;
			        	 	if(val>9) {
			        	 		val=0;
			        	 	}
			        	 	tempButton.setText(String.valueOf(val));
			          }
			       });
				panel.add(buttongrid[i][j]);
			}	
		}
	}
}*/
